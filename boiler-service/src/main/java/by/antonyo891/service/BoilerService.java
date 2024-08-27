package by.antonyo891.service;

import by.antonyo891.model.Boiler;
import by.antonyo891.repository.BoilerNTDRepository;
import by.antonyo891.repository.BoilerRepository;
import by.antonyo891.repository.TypeOfBoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class BoilerService implements BoilerServiceInterface{
    @Autowired
    BoilerRepository boilerRepository;
    @Autowired
    TypeOfBoilerRepository typeOfBoilerRepository;
    @Autowired
    BoilerNTDRepository ntdRepository;

    @Override
    public List<Boiler> findAll() {

        return boilerRepository.findAll();
    }

    @Override
    public Boiler getBoilerByName(String name) {
        return boilerRepository.findByName(name).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Boiler with name " + name + " not found;")
        );
    }
}
