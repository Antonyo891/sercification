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
import java.util.UUID;

@Service
public class BoilerService implements BoilerServiceInterface{
    @Autowired
    private BoilerRepository boilerRepository;
    @Autowired
    private TypeOfBoilerRepository typeOfBoilerRepository;
    @Autowired
    private BoilerNTDRepository ntdRepository;

    @Override
    public List<Boiler> findAll() {
        return boilerRepository.findAll();
    }

    @Override
    public Boiler getBoiler(String name) {
        return boilerRepository.findByName(name).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Boiler with name " + name + " not found;")
        );
    }

    @Override
    public Boiler getBoiler(UUID boilerId) {
        return boilerRepository.findById(boilerId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Boiler with UUID " + boilerId + " not found;"));
    }
}
