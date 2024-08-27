package by.antonyo891.service;

import by.antonyo891.model.Boiler;
import by.antonyo891.model.BoilerCondition;
import by.antonyo891.model.BoilerConditionAccordingNTD;
import by.antonyo891.repository.BoilerNTDRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoilerNTDService {
    @Autowired
    BoilerNTDRepository boilerNTDRepository;
    @Autowired
    BoilerConditionService boilerConditionService;

    public Map<BoilerCondition,BoilerConditionAccordingNTD> getBoilerNTDByCondition(BoilerCondition boilerCondition){
        Boiler boiler = boilerCondition.getBoilerCondition();
        Integer steamConsumption = boilerCondition.getSteamConsumption();
        ArrayList<BoilerConditionAccordingNTD> boilerNTD = boilerNTDRepository.findByBoilerNTD(boiler);
        BoilerConditionAccordingNTD ntdForAssess = boilerNTD.stream().filter(ntd -> ntd.getSteamConsumption() == boilerCondition.getSteamConsumption())
                .findFirst().orElse(null);
        boilerNTD.sort(Comparator.comparing(BoilerConditionAccordingNTD::getFuelConsumption));
        Map<BoilerCondition,BoilerConditionAccordingNTD> map = new HashMap<>();
        if (ntdForAssess!=null) {
            map.put(boilerCondition,ntdForAssess);
        } else {
            for (int i = 0; i < boilerNTD.size(); i++) {
                if (boilerNTD.get(i).getSteamConsumption()>steamConsumption){
                    BoilerConditionAccordingNTD interpolationNTD =
                            getInterpolation(boilerNTD.get(i - 1), boilerNTD.get(i), steamConsumption);
                    map.put(boilerCondition,interpolationNTD);
                    return map;
                }
            }
        }
        return map;
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

    public Map<BoilerCondition, BoilerConditionAccordingNTD> assessCondition() {
        Set<BoilerCondition> boilerConditionByNow = boilerConditionService.getBoilerConditionByNow();
        Map<BoilerCondition, BoilerConditionAccordingNTD> map = new HashMap<>();
        for (BoilerCondition boilerCondition : boilerConditionByNow) {
            map.putAll(getBoilerNTDByCondition(boilerCondition));
        }
        return map;
    }
}
