package by.antonyo891;

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
//        TypeOfBoilerRepository typeOfBoilerRepository = context.getBean(TypeOfBoilerRepository.class);
//        TypeOfBoiler type1 = new TypeOfBoiler(
//                "БКЗ-210-140",210,140,550);
//        TypeOfBoiler type2 = new TypeOfBoiler(
//                "БКЗ-420-140",420,140,550);
//        type1 = typeOfBoilerRepository.save(type1);
//        type2 = typeOfBoilerRepository.save(type2);
//        Boiler boiler1 = new Boiler("station # 1", type1);
//        Boiler boiler5 = new Boiler("station # 5", type2);
//        System.out.println("Boilers: ");
//        System.out.println(boilerRepository.save(boiler1));
//        System.out.println(boilerRepository.save(boiler5));
//        float kpdMin1 = 88.0f;
//        int steam1 = 100;
//        float fuel1;
//        int steam5 = 210;
//        float kpdMin5 = 89.0f;
//        float fuel5;
//
//        Set<BoilerConditionAccordingNTD> boilerConditions1 = new HashSet<>();
//        Set<BoilerConditionAccordingNTD> boilerConditions5 = new HashSet<>();
//        for (int i = 0; i<10; i++){
//            steam1 = steam1+11;
//            kpdMin1 = kpdMin1 + 0.8f;
//            fuel1 = (steam1*0.6f)/(kpdMin1*7/100);
//            steam5 = steam5+21;
//            kpdMin5 = kpdMin5 + 0.8f;
//            fuel5 = (steam5*0.6f)/(kpdMin5*7/100);
//            boilerConditions1.add(new BoilerConditionAccordingNTD(steam1,fuel1,kpdMin1,boiler1));
//            boilerConditions5.add(new BoilerConditionAccordingNTD(steam5,fuel5,kpdMin5,boiler5));
//        }
//        System.out.println("boilerCondition: ");
//        System.out.println(boilerNTDRepository.saveAll(boilerConditions1));
//        System.out.println(boilerNTDRepository.saveAll(boilerConditions5));
//
//        Set<BoilerCondition> boiler1Condition = new HashSet<>();
//        Set<BoilerCondition> boiler5Condition = new HashSet<>();
//        for (int i = 0; i < 10; i++) {
//            steam1 = 188;
//            kpdMin1 = 94.4f;
//            fuel1 = (steam1*0.6f)/(kpdMin1*7/100);
//            steam5 = 378;
//            kpdMin5 = 95.4f;
//            fuel5 = (steam5*0.6f)/(kpdMin5*7/100);
//            boiler1Condition.add(new BoilerCondition(LocalDateTime.now().plusMinutes(1l+i).withSecond(0),
//                    steam1,fuel1,kpdMin1,boiler1));
//            boiler5Condition.add(new BoilerCondition(LocalDateTime.now().plusMinutes(1l+i).withSecond(0),
//                    steam5,fuel5,kpdMin5,boiler5));
//        }
//        System.out.println("boilerCondition");
//        System.out.println(boilerConditionRepository.saveAll(boiler1Condition));
//        System.out.println(boilerConditionRepository.saveAll(boiler5Condition));
    }
}