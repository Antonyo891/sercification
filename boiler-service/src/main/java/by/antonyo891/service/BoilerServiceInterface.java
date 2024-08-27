package by.antonyo891.service;

import by.antonyo891.model.Boiler;

import java.util.List;

public interface BoilerServiceInterface {
    List<Boiler> findAll();
    Boiler getBoilerByName(String name);
}
