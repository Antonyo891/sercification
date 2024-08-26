package by.antonyo891.api;

import by.antonyo891.model.Boiler;
import by.antonyo891.repository.BoilerRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@Data
@RequestMapping("/boiler")
public class BoilerController {
    @Autowired
    private BoilerRepository boilerRepository;
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
    List<Boiler> boilers = boilerRepository.findAll();
    log.info("Boilers :{}", boilers);
    return ResponseEntity.status(HttpStatus.OK)
            .body(boilers);
}

}
