package by.antonyo891.api;

import by.antonyo891.model.BoilerConditionAccordingNTD;
import by.antonyo891.service.BoilerNTDService;
import by.antonyo891.service.BoilerServiceInterface;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@Slf4j
@Data
@RequestMapping("/ntd")
public class BoilerNTDController {
    @Autowired
    private BoilerServiceInterface boilerService;
    @Autowired
    BoilerNTDService boilerNTDService;
@GetMapping(path = "/{boilerId}")
@ResponseBody
public ResponseEntity<List<BoilerConditionAccordingNTD>> getNTDbyBoiler(@PathVariable("boilerId") UUID boilerId) {
    log.info("Request NTD for boiler with id: {}",boilerId);
    List<BoilerConditionAccordingNTD> ntds = boilerNTDService.findNTDByBoiler(boilerId);
    log.info("BoilersNTD :{}", ntds.stream().map(BoilerConditionAccordingNTD::getId));
    return ResponseEntity.status(HttpStatus.OK)
            .body(ntds);
}
    @PostMapping("/{boilerId}")
    public ResponseEntity<BoilerConditionAccordingNTD>
    addNTD(@PathVariable("boilerId") UUID boilerId,
           @RequestBody BoilerConditionAccordingNTD NTD)
    {
        log.info("Request to add NTD {} for boiler with id: {}",NTD,boilerId);
        BoilerConditionAccordingNTD ntdToAdd = boilerNTDService.addNTD(NTD, boilerId);
        return  ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(ntdToAdd);
    }


}
