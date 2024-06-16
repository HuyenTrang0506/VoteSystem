package net.codejava.service.service_interface;

import net.codejava.dto.CandidateDTO;
import net.codejava.entity.Candidate;
import net.codejava.entity.Election;

import java.util.List;

public interface CandidateService {
    Candidate save(Candidate candidate);
    Candidate findCandidateById(Long id);
    Candidate update(Candidate candidate);
    Boolean delete(Candidate candidate);
    CandidateDTO increaseBallotCount(Long id);

    Boolean delete(Long id);

}
