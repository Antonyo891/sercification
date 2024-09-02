package by.antonyo891;

import by.antonyo891.api.BoilerController;
import by.antonyo891.api.BoilerNTDController;
import by.antonyo891.model.Boiler;
import by.antonyo891.model.BoilerCondition;
import by.antonyo891.model.BoilerConditionAccordingNTD;
import by.antonyo891.model.TypeOfBoiler;
import by.antonyo891.repository.BoilerConditionRepository;
import by.antonyo891.repository.BoilerNTDRepository;
import by.antonyo891.repository.BoilerRepository;
import by.antonyo891.repository.TypeOfBoilerRepository;
import by.antonyo891.service.BoilerConditionService;
import by.antonyo891.service.BoilerNTDService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BoilerApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BoilerApplication.class, args);
//        BoilerConditionRepository boilerConditionRepository = context.getBean(BoilerConditionRepository.class);
//        BoilerNTDRepository boilerNTDRepository = context.getBean(BoilerNTDRepository.class);
//        BoilerRepository boilerRepository = context.getBean(BoilerRepository.class);
        BoilerController boilerController = context.getBean(BoilerController.class);
        BoilerNTDController NTDController = context.getBean(BoilerNTDController.class);
//        TypeOfBoilerRepository typeOfBoilerRepository = context.getBean(TypeOfBoilerRepository.class);
//        TypeOfBoiler type1 = typeOfBoilerRepository.findByName("БКЗ-210-140").get();
//        TypeOfBoiler type2 = typeOfBoilerRepository.findByName("БКЗ-420-140").get();
//        type1 = typeOfBoilerRepository.save(type1);
//        type2 = typeOfBoilerRepository.save(type2);
//        Boiler boiler1 = boilerRepository.findByName("station # 1").get();
//        Boiler boiler5 = boilerRepository.findByName("station # 5").get();
//        Boiler boiler2 = boilerRepository.findByName("station # 2").get();
//        float kpdMin1 = 88.0f;
//        int steam1 = 100;
//        float fuel1;
//        float kpdMin2 = 89.0f;
//        int steam2 = 100;
//        float fuel2;
//        int steam5 = 210;
//        float kpdMin5 = 89.6f;
//        float fuel5;
//
//        Set<BoilerConditionAccordingNTD> boilerNTD1 = new HashSet<>();
//        Set<BoilerConditionAccordingNTD> boilerNTD2 = new HashSet<>();
//        Set<BoilerConditionAccordingNTD> boilerNTD5 = new HashSet<>();
//        for (int i = 0; i<=10; i++){
//            fuel1 = 100*(steam1*0.6f)/(kpdMin1*7);
//            fuel2 = 100*(steam2*0.6f)/(kpdMin2*7);
//            fuel5 = 100*(steam5*0.6f)/(kpdMin5*7);
//            boilerNTD1.add(new BoilerConditionAccordingNTD(steam1,fuel1,kpdMin1,boiler1));
//            boilerNTD2.add(new BoilerConditionAccordingNTD(steam2,fuel2,kpdMin2,boiler2));
//            boilerNTD5.add(new BoilerConditionAccordingNTD(steam5,fuel5,kpdMin5,boiler5));
//            steam1 += 11;
//            kpdMin1 += 0.8f;
//            steam2 += 11;
//            kpdMin2 += 0.8f;
//            steam5 += 21;
//            kpdMin5 += 0.8f;
//        }
//        boilerNTDRepository.saveAll(boilerNTD2);
//        boilerNTDRepository.saveAll(boilerNTD1);
//        boilerNTDRepository.saveAll(boilerNTD5);
//            steam2 = 116;
//            kpdMin2 = 90.2f;
//            fuel2 = 100*(steam2*0.6f)/(kpdMin2*7);
//            steam1 = 188;
//            kpdMin1 = 94.4f;
//            fuel1 = (steam1*0.6f)/(kpdMin1*7/100);
//            steam5 = 378;
//            kpdMin5 = 95.4f;
//            fuel5 = (steam5*0.6f)/(kpdMin5*7/100);
//            boilerConditionRepository.save(
//                    new BoilerCondition(LocalDateTime.now().withSecond(0).withNano(0),
//                    steam1,fuel1,kpdMin1,boiler1));
//            boilerConditionRepository.save(
//                    new BoilerCondition(LocalDateTime.now().withNano(0).withSecond(0),
//                    steam5,fuel5,kpdMin5,boiler5));
//            boilerConditionRepository.save(
//                    new BoilerCondition(LocalDateTime.now().withSecond(0)
//                .withNano(0),steam2,fuel2,kpdMin2,boiler2))
    }
}