package by.antonyo891.service;

import by.antonyo891.model.Boiler;
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

    public TypeOfBoiler getTypeByName(String typeName){
        return typeOfBoilerRepository.findByName(typeName)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Type of boiler with name " + typeName + " not found;"));
    }
    //    public List<TypeOfBoiler> getAllByBoilers(Set<Boiler> boilers){
//        return boilers.stream()
//                .map(b->typeOfBoilerRepository.findAnyWithBoilersContain(b)
//                        .orElseThrow(
//                                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,
//                                        "Type of Boiler with boiler " + b + " not found;")
//                        )).toList();
//    }
}
