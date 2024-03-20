package net.codejava.service.service;

import net.codejava.dto.CandidateDTO;
import net.codejava.entity.Candidate;

public interface CandidateService {
    Candidate save(CandidateDTO candidateDTO);
        Candidate findCandidateById(Long id);
    Candidate update(Candidate candidate);
    Boolean delete(Candidate candidate);

    Boolean delete(Long id);


}
