package net.codejava.service.service_interface;

import net.codejava.dto.CandidateDTO;
import net.codejava.entity.Candidate;
import net.codejava.entity.Election;

import java.util.List;

public interface CandidateService {
    Candidate save(CandidateDTO candidateDTO);
        Candidate findCandidateById(Long id);
    Candidate update(Candidate candidate);
    Boolean delete(Candidate candidate);

    Boolean delete(Long id);
    List<Candidate> findALLCandidateByElection(Election election);
    List<Candidate> findALLCandidateByElectionId(Long election_id);
    Candidate findCandidateByElectionAndName(Election election, String name);
    Candidate findCandidateByElectionAndId(Election election, Long id);
}
