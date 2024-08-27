package by.antonyo891.service;

import by.antonyo891.model.Boiler;
import by.antonyo891.model.BoilerCondition;

import java.time.LocalDateTime;
import java.util.Set;

public interface BoilerConditionServiceInterface {
    Set<BoilerCondition> getBoilerConditionByNow();
    BoilerCondition getConditionNow(Boiler boiler);
}
