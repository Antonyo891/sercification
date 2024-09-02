package by.antonyo891.service;

import by.antonyo891.BoilerProperties;
import by.antonyo891.model.Boiler;
import by.antonyo891.model.BoilerCondition;
import by.antonyo891.model.TypeOfBoiler;
import by.antonyo891.repository.BoilerConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BoilerConditionService implements BoilerConditionServiceInterface  {
    @Autowired
    private BoilerConditionRepository conditionRepository;
    @Autowired
    private BoilerProperties boilerProperties;
    @Autowired
    private TypeOfBoilerService typeOfBoilerService;
    @Autowired
    private BoilerServiceInterface boilerServiceInterface;
    @Override
    public Set<BoilerCondition> getBoilerConditionByNow(){
        List<TypeOfBoiler> typeOfBoilerList = typeOfBoilerService.getAll();
        Set<Boiler> boilers = new HashSet<>(boilerServiceInterface.findAll());
        return new HashSet<>(boilers.stream()
                .map(this::getConditionNow).toList());


    }

    @Override
    public BoilerCondition getConditionNow(Boiler boiler) {
        List<TypeOfBoiler> typeOfBoilerList = typeOfBoilerService.getAll();
        return conditionRepository.findFirst1ByBoilerConditionOrderByTimeDesc(boiler)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Not found condition"));
    }
}
