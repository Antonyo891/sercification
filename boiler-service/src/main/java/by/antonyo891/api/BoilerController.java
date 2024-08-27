package by.antonyo891.api;

import by.antonyo891.model.Boiler;
import by.antonyo891.model.BoilerCondition;
import by.antonyo891.model.BoilerConditionAccordingNTD;
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
import java.util.List;
import java.util.Map;
import java.util.Set;

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
//    @Autowired
//    private BoilersNTDService boilersNTDService;

//    @PostMapping
//    public ResponseEntity<Boiler> addBoiler(@RequestBody Boiler boiler){
//        log.info("Request to add a boiler: {}",boiler);
//        Boiler newBoiler = boilerService.addBoiler(boiler);
//        log.info("Boiler {} add.",newBoiler);
//        return ResponseEntity.status(HttpStatus.OK).body(newBoiler);
//    }
@GetMapping(path = "/boilers")
@ResponseBody
public ResponseEntity<List<Boiler>> getBoilers() {
    log.info("Request boilers");
    List<Boiler> boilers = boilerService.findAll();
    log.info("Boilers :{}", boilers);
    return ResponseEntity.status(HttpStatus.OK)
            .body(boilers);
}

    @GetMapping(path = "/conditions")
    @ResponseBody
    public ResponseEntity<Set<BoilerCondition>> getBoilersCondition() {
        log.info("Request conditions by now.");
        Set<BoilerCondition> conditions = boilerConditionService.getBoilerConditionByNow();
        log.info("Boilers :{}", conditions);
        return ResponseEntity.status(HttpStatus.OK)
                .body(conditions);
    }
    @GetMapping(path = "/conditions/NTD")
    @ResponseBody
    public ResponseEntity<Map<BoilerCondition, BoilerConditionAccordingNTD>> getAssess() {
        log.info("Request conditions by now.");
        Map<BoilerCondition, BoilerConditionAccordingNTD> conditions =
                boilerNTDService.assessCondition();
        log.info("Boilers :{}", conditions);
        return ResponseEntity.status(HttpStatus.OK)
                .body(conditions);
    }

}
