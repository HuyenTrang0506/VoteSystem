package net.codejava.controller;

import net.codejava.dto.CandidateDTO;
import net.codejava.entity.Candidate;
import net.codejava.service.service_interface.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {
    private final CandidateService candidateService;
    private ModelMapper modelMapper;
    @Autowired
    public CandidateController(CandidateService candidateService, ModelMapper modelMapper) {
        this.candidateService = candidateService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public ResponseEntity<Candidate> saveCandidate(@RequestBody CandidateDTO candidateDTO) {

        Candidate candidate= modelMapper.map(candidateDTO, Candidate.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(candidate);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable Long id) {
        Candidate candidate = candidateService.findCandidateById(id);
        return ResponseEntity.ok(candidate);
    }





//
//    Candidate save(CandidateDTO candidateDTO);
//    Candidate findCandidateById(Long id);
//    Candidate update(Candidate candidate);
//    Boolean delete(Candidate candidate);
//
//    Boolean delete(Long id);
//    List<Candidate> findALLCandidateByElection(Election election);
//    Candidate findCandidateByElectionAndName(Election election, String name);
//    Candidate findCandidateByElectionAndId(Election election, Long id);
}
