package net.codejava.repository;

import net.codejava.entity.Candidate;
import net.codejava.entity.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> findALLCandidateByElection(Election election);
    List<Candidate> findALLCandidateByElectionId(Long election_id);


    Candidate findCandidateByElectionAndName(Election election, String name);

    Candidate findCandidateByElectionAndId(Election election, Long id);
}
