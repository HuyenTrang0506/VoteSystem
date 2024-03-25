package net.codejava.controller;

import net.codejava.dto.ResultDTO;
import net.codejava.entity.Result;
import net.codejava.service.service_interface.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
public class ResultController {
    @Autowired
    private ResultService resultService;
    @PostMapping
    public ResponseEntity<Result> saveResult(@RequestBody ResultDTO resultDTO) {
        Result result = resultService.save(resultDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }
    @GetMapping("/election/{election_id}")
    public ResponseEntity<Result> getResultByElectionId(@PathVariable Long election_id) {
        Result results= resultService.findResultByElectionId(election_id);
        return ResponseEntity.ok(results);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Long id) {
        Result result = resultService.findResultById(id);
        return ResponseEntity.ok(result);
    }

}
