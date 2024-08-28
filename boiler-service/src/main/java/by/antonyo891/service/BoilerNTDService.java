package by.antonyo891.service;

import by.antonyo891.BoilerProperties;
import by.antonyo891.model.Boiler;
import by.antonyo891.model.BoilerCondition;
import by.antonyo891.model.BoilerConditionAccordingNTD;
import by.antonyo891.model.TypeOfBoiler;
import by.antonyo891.repository.BoilerNTDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BoilerNTDService {
    @Autowired
    BoilerNTDRepository boilerNTDRepository;
    @Autowired
    BoilerConditionService boilerConditionService;
    @Autowired
    BoilerProperties boilerProperties;

    public Map<BoilerCondition,BoilerConditionAccordingNTD> getBoilerNTDByCondition(BoilerCondition boilerCondition){
        Boiler boiler = boilerCondition.getBoilerCondition();
        Integer steamConsumption = boilerCondition.getSteamConsumption();
        Map<BoilerCondition,BoilerConditionAccordingNTD> result = new HashMap<>();
        result.put(boilerCondition,findNTD(boiler,steamConsumption));
        return result;
    }
    private BoilerConditionAccordingNTD getInterpolation(BoilerConditionAccordingNTD first,
                                                         BoilerConditionAccordingNTD second,
                                                         Integer factSteamConsumption){
        float fraction = (float) ((factSteamConsumption-first.getSteamConsumption()) /
                (second.getSteamConsumption() - factSteamConsumption));
        float fuelConsumption = (float) (second.getFuelConsumption() -
                first.getFuelConsumption())*fraction + first.getFuelConsumption();
        Float efficiencyCoefficient = (float) ((factSteamConsumption * 0.6 / (fuelConsumption * 7))*100);
            BoilerConditionAccordingNTD resultNTD = new BoilerConditionAccordingNTD();
        resultNTD.setFuelConsumption(fuelConsumption);
        resultNTD.setEfficiencyCoefficient(efficiencyCoefficient);
        resultNTD.setSteamConsumption(factSteamConsumption);
        resultNTD.setBoilerNTD(first.getBoilerNTD());
        return resultNTD;
    }
    public List<BoilerConditionAccordingNTD> getOptimalRegime(List<BoilerCondition> boilerConditions){
        Integer sumSteamConsumption = boilerConditions.stream()
                .map(BoilerCondition::getSteamConsumption).toList()
                .stream().reduce(0,Integer::sum);
        Float sumFuel = boilerConditions.stream()
                .map(BoilerCondition::getFuelConsumption).toList().stream()
                .reduce(0f,Float::sum);
        List<BoilerConditionAccordingNTD> boilerConditionAccordingNTDList =
                boilerConditions.stream()
                        .map(c->getBoilerNTDByCondition(c).values().stream()
                                .findFirst()
                                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        getClass().getName()+" not found boilerConditions")))
                        .toList();
        return findOptimalRegime(boilerConditionAccordingNTDList,
                sumSteamConsumption);
    }
    private List<BoilerConditionAccordingNTD> findOptimalRegime(List<BoilerConditionAccordingNTD> NTD,
                                                                int steamConsumption){
        List<BoilerConditionAccordingNTD> resultList = new ArrayList<>(NTD);
        float minFuel = NTD.stream().map(BoilerConditionAccordingNTD::getFuelConsumption)
                .reduce(0f,Float::sum);
        BoilerConditionAccordingNTD conditionAccordingNTD = NTD.removeFirst();
        Integer remainingSteamConsumption = NTD.stream().map(BoilerConditionAccordingNTD::getBoilerNTD)
                        .map(Boiler::getTypeOfBoiler)
                        .map(TypeOfBoiler::getNominalSteamCapacity)
                .map(s-> (int) (s*boilerProperties.getMINIMUM_BOILER_LOAD()))
                        .reduce(0,Integer::sum);
        Integer maxSteamConsumption = steamConsumption -
                (int)(remainingSteamConsumption* boilerProperties.getMINIMUM_BOILER_LOAD());
        Integer minSteamConsumption = steamConsumption - remainingSteamConsumption;
        Integer technicalMax = conditionAccordingNTD.getBoilerNTD()
                .getTypeOfBoiler().getNominalSteamCapacity();
        Integer technicalMin = (int) (technicalMax * boilerProperties.getMINIMUM_BOILER_LOAD());
        if (maxSteamConsumption>technicalMax) maxSteamConsumption = technicalMax;
        if (minSteamConsumption<technicalMin) minSteamConsumption = technicalMin;
        float fuelTemp;
        BoilerConditionAccordingNTD NTDFirst;
        if (NTD.size()<2) {
            BoilerConditionAccordingNTD NTDSecond;
            for (int i = minSteamConsumption; i < maxSteamConsumption ; i+=boilerProperties.getBASIC_STEAM_LOAD_STEP()) {
                NTDFirst = findNTD(conditionAccordingNTD.getBoilerNTD(),i);
                NTDSecond = findNTD(NTD.getFirst().getBoilerNTD(), steamConsumption - i);
                fuelTemp = NTDFirst.getFuelConsumption() + NTDSecond.getFuelConsumption();
                if (fuelTemp<minFuel) {
                    minFuel = fuelTemp;
                    resultList = new ArrayList<>(List.of(NTDFirst,NTDSecond));
                }
            }
            return resultList;
        }
        List<BoilerConditionAccordingNTD> otherNTD;
        for (int i = minSteamConsumption; i < maxSteamConsumption; i+=boilerProperties.getBASIC_STEAM_LOAD_STEP()) {
            NTDFirst = findNTD(conditionAccordingNTD.getBoilerNTD(),i);
            otherNTD = findOptimalRegime(NTD,remainingSteamConsumption);
            fuelTemp = NTDFirst.getFuelConsumption() +
                    otherNTD.stream()
                            .map(BoilerConditionAccordingNTD::getFuelConsumption)
                            .reduce(0f,Float::sum);
            if (fuelTemp<minFuel){
                minFuel =fuelTemp;
                resultList = new ArrayList<>();
                resultList.add(NTDFirst);
                resultList.addAll(otherNTD);
            }
        }
        return resultList;
    }

    private BoilerConditionAccordingNTD findNTD(Boiler boiler, Integer steamConsumption){
        ArrayList<BoilerConditionAccordingNTD> boilerNTD = boilerNTDRepository.findByBoilerNTD(boiler);
        BoilerConditionAccordingNTD foundNTD =
                boilerNTD.stream().
                        filter(ntd ->
                                Objects.equals(ntd.getSteamConsumption(), steamConsumption))
                        .findFirst().orElse(null);
        if (foundNTD!=null) {
            return foundNTD;
        } else {
            boilerNTD.sort(Comparator.comparing(BoilerConditionAccordingNTD::getFuelConsumption));
            for (int i = 0; i < boilerNTD.size(); i++) {
                if (boilerNTD.get(i).getSteamConsumption()>steamConsumption){
                    foundNTD = getInterpolation(boilerNTD.get(i - 1), boilerNTD.get(i), steamConsumption);
                    boilerNTDRepository.save(foundNTD);
                    return foundNTD;
                }
            }
        }
        return foundNTD;
    }


    public Map<BoilerCondition, BoilerConditionAccordingNTD> assessCondition() {
        Set<BoilerCondition> boilerConditionByNow = boilerConditionService.getBoilerConditionByNow();
        Map<BoilerCondition, BoilerConditionAccordingNTD> map = new HashMap<>();
        for (BoilerCondition boilerCondition : boilerConditionByNow) {
            map.putAll(getBoilerNTDByCondition(boilerCondition));
        }
        return map;
    }
}
