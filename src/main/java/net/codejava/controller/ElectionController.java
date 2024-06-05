package net.codejava.controller;

import net.codejava.dto.AuthResponse;
import net.codejava.dto.ElectionDTO;
import net.codejava.dto.VoterDTO;
import net.codejava.entity.Election;
import net.codejava.entity.User;
import net.codejava.exception_handler.CustomErrorResponse;
import net.codejava.service.service_interface.ElectionService;
import net.codejava.service.service_interface.VoterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/elections")
public class ElectionController {
    private final ElectionService electionService;
    private final VoterService voterService;
    @Autowired
    public ElectionController(ElectionService electionService,  VoterService voterService) {
        this.electionService = electionService;
        this.voterService = voterService;
    }
    @GetMapping()
    public List<ElectionDTO> getAllElections() {


       return electionService.getAllElection();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Election> getElectionById(@PathVariable Long id) {
        Election election = electionService.findElectionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(election);
    }


    @PostMapping
    public ResponseEntity<ElectionDTO> saveElection(@RequestBody ElectionDTO electionDTO) {
        ElectionDTO electionDTO1 = electionService.save(electionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(electionDTO1);
    }
    //save voter with election id, user id and its permission
    @PostMapping("/voter-permission/{id}")
    public List<VoterDTO> saveVoterPermission(@PathVariable Long id,@RequestBody List<VoterDTO> listVoterDTO) {
        return  voterService.saveVoterPermission(id,listVoterDTO);

    }


//

}
