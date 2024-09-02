package by.antonyo891.api;

import by.antonyo891.model.Boiler;
import by.antonyo891.model.BoilerCondition;
import by.antonyo891.model.BoilerConditionAccordingNTD;
import by.antonyo891.model.TypeOfBoiler;
import by.antonyo891.repository.BoilerRepository;
import by.antonyo891.service.BoilerConditionServiceInterface;
import by.antonyo891.service.BoilerNTDService;
import by.antonyo891.service.BoilerServiceInterface;
import by.antonyo891.service.TypeOfBoilerService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
@Data
@RequestMapping("/boiler")
public class BoilerController {
    @Autowired
    private BoilerServiceInterface boilerService;
    @Autowired
    BoilerConditionServiceInterface boilerConditionService;
    @Autowired
    TypeOfBoilerService typeOfBoilerService;
    @Autowired
    BoilerNTDService boilerNTDService;
    @GetMapping(path = "/boilers")
    @ResponseBody
    public ResponseEntity<List<Boiler>> getBoilers() {
        log.info("Request boilers");
        List<Boiler> boilers = boilerService.findAll();
        log.info("Boilers :{}", boilers.stream().map(Boiler::getName));
        return ResponseEntity.status(HttpStatus.OK)
                .body(boilers);
    }

    @GetMapping(path = "/conditions")
    @ResponseBody
    public ResponseEntity<Set<BoilerCondition>> getBoilersCondition() {
        log.info("Request conditions by now.");
        Set<BoilerCondition> conditions = boilerConditionService.getBoilerConditionByNow();
        log.info("Boilers :{}", conditions.stream().map(BoilerCondition::getId));
        return ResponseEntity.status(HttpStatus.OK)
                .body(conditions);
    }
    @GetMapping(path = "/NTD/assess")
    @ResponseBody
    public ResponseEntity<Map<BoilerCondition, BoilerConditionAccordingNTD>> getAssess() {
        log.info("Request conditions by now.");
        Map<BoilerCondition, BoilerConditionAccordingNTD> conditions =
                boilerNTDService.assessCondition();
        return ResponseEntity.status(HttpStatus.OK)
                .body(conditions);
    }
    @GetMapping(path = "/NTD/optimal")
    @ResponseBody
    public ResponseEntity<Map<List<BoilerCondition>,List<BoilerConditionAccordingNTD>>> getOptimalRegime() {
        log.info("Request optimal regime by now.");
        List<BoilerCondition> conditions = boilerConditionService.getBoilerConditionByNow().stream().toList();
        log.info("Boilers condition : {}", conditions.stream().map(BoilerCondition::getSteamConsumption));
        List<BoilerConditionAccordingNTD> optimalRegime = boilerNTDService.getOptimalRegime(conditions);
        log.info("Boilers optimal Regime: {}", optimalRegime.stream().map(BoilerConditionAccordingNTD::getSteamConsumption));
        Map<List<BoilerCondition>,List<BoilerConditionAccordingNTD>> result = new HashMap<>();
        result.put(conditions,optimalRegime);
        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

}
