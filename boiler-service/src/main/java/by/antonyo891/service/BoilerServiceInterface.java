package by.antonyo891.service;

import by.antonyo891.model.Boiler;

import java.util.List;
import java.util.UUID;

public interface BoilerServiceInterface {
    List<Boiler> findAll();
    Boiler getBoiler(String name);

    Boiler getBoiler(UUID boilerId);

}
