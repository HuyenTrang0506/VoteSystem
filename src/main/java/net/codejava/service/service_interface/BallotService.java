package net.codejava.service.service_interface;

import net.codejava.dto.BallotDTO;
import net.codejava.entity.Ballot;
import net.codejava.entity.Election;

import java.util.List;

public interface BallotService {
    Ballot save(BallotDTO ballotDTO);

    Ballot findBallotById(Long id);
    Ballot update(Ballot ballot);
    boolean delete(Ballot ballot);
    boolean deleteBallotById(Long id);
    List<Ballot> findAllBallotByElection(Election election);
    List<Ballot> findBallotByElectionId(Long election_id);
    List<Ballot> findAllBallot();
    List<Ballot> findBallotByCandidateId(Long voter_id);

}
