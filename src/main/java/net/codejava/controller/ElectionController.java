package net.codejava.controller;

import net.codejava.dto.ElectionDTO;
import net.codejava.entity.Election;
import net.codejava.service.service_interface.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/elections")
public class ElectionController {
    private final ElectionService electionService;
    public ElectionController(ElectionService electionService) {
        this.electionService = electionService;
    }
    @GetMapping()
    public ResponseEntity<Object> getAllElection() {
        List<Election> elections = electionService.getAllElection();
        return ResponseEntity.ok(elections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Election> getElectionById(@PathVariable Long id) {
        Election election = electionService.findElectionById(id);
        return ResponseEntity.ok(election);
    }

    @PostMapping
    public ResponseEntity<Election> saveElection(@RequestBody ElectionDTO ElectionDTO) {
        Election createdElection = electionService.save(ElectionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdElection);
    }

}
