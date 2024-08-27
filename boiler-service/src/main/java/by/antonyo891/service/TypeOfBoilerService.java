package by.antonyo891.service;

import by.antonyo891.repository.TypeOfBoilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeOfBoilerService {
    @Autowired
    TypeOfBoilerRepository typeOfBoilerRepository;
}
