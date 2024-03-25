package net.codejava.controller;

import net.codejava.dto.BallotDTO;
import net.codejava.entity.Ballot;
import net.codejava.service.service_interface.BallotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ballots")
public class BallotController {
    @Autowired
    private BallotService ballotService;
    @GetMapping("/election/{election_id}")
    public ResponseEntity<Object> getAllBallotByElectionId(@PathVariable Long election_id) {
        List<Ballot> ballots= ballotService.findBallotByElectionId(election_id);
        return ResponseEntity.ok(ballots);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ballot> getBallotById(@PathVariable Long id) {
        Ballot ballot = ballotService.findBallotById(id);
        return ResponseEntity.ok(ballot);
    }
    @PostMapping
    public ResponseEntity<Ballot> saveBallot(@RequestBody BallotDTO ballotDTO) {
        Ballot ballot = ballotService.save(ballotDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ballot);

    }
}
