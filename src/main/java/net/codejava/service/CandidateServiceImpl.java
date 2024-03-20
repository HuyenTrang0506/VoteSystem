package net.codejava.service;

import net.codejava.dto.CandidateDTO;
import net.codejava.entity.Ballot;
import net.codejava.entity.Candidate;
import net.codejava.repository.CandidateRepository;
import net.codejava.service.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Override
    public Candidate save(CandidateDTO candidateDTO) {

        ModelMapper modelMapper = new ModelMapper();
        Candidate candidate= modelMapper.map(candidateDTO, Candidate.class);
        return candidateRepository.save(candidate);
    }

    @Override
    public Candidate findCandidateById(Long id) {
        Optional<Candidate> q = candidateRepository.findById(id);
        Candidate candidate = null;
        if (q.isPresent()) {
            candidate = q.get();
        }
        return candidate;
    }

    @Override
    public Candidate update(Candidate candidate) {
        return null;
    }

    @Override
    public Boolean delete(Candidate candidate) {
        try {
            candidateRepository.delete(candidate);
            return true;
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return false; // Return false if deletion fails
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            candidateRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return false; // Return false if deletion fails
        }
    }
}
