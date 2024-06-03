package net.codejava.repository;

import net.codejava.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, Long> {

//    List<Ballot> findAllByVoter(User voter);
//    List<Ballot> findAllByVoterId(Long voterId);
//    List<Ballot> findAllByCandidate(Candidate candidate);
    List<Ballot> findAllByCandidateId(Long candidateId);
    List<Ballot> findAllByElection(Election election);
    List<Ballot> findAllByElectionId(Long electionId);


}
