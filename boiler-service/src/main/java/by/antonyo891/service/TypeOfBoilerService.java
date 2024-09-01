package by.antonyo891.service;

import by.antonyo891.model.Boiler;
import by.antonyo891.model.TypeOfBoiler;
import by.antonyo891.repository.TypeOfBoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TypeOfBoilerService {
    @Autowired
    TypeOfBoilerRepository typeOfBoilerRepository;

    public TypeOfBoiler getTypeByBoiler(Boiler boiler){
        return typeOfBoilerRepository.findByBoilers(boiler)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Type of boiler with boiler " + boiler + " not found;"));
    }
}
