package by.antonyo891.service;

import by.antonyo891.model.TypeOfBoiler;
import by.antonyo891.repository.TypeOfBoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
public class TypeOfBoilerService {
    @Autowired
    TypeOfBoilerRepository typeOfBoilerRepository;

    public List<TypeOfBoiler> getAll() {
        return typeOfBoilerRepository.findAll();
    }

//    public TypeOfBoiler getTypeByName(String boilersName){
//        return typeOfBoilerRepository.findByBoilersName(boilersName)
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
//                        "Type of boiler with name " + boilersName + " not found;"));
//    }
}
