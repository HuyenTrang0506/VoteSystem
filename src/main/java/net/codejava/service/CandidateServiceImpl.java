package net.codejava.service;

import net.codejava.dto.CandidateDTO;
import net.codejava.entity.Candidate;
import net.codejava.entity.Election;
import net.codejava.repository.CandidateRepository;
import net.codejava.service.service_interface.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Override
    public Candidate save(Candidate candidate) {


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
    public CandidateDTO increaseBallotCount(Long id) {
        Optional<Candidate> candidateOptional = candidateRepository.findById(id);
        if (!candidateOptional.isPresent()) {
            return null; // or throw an exception
        }

        Candidate candidate = candidateOptional.get();
        candidate.setBallotCount(candidate.getBallotCount() + 1);
        Candidate updatedCandidate = candidateRepository.save(candidate);

        return new CandidateDTO(
                updatedCandidate.getId(),
                updatedCandidate.getName(),
                updatedCandidate.getDescription(),
                updatedCandidate.getImage(),
                updatedCandidate.getContactInformation(),
                updatedCandidate.getBallotCount()
        );
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
    private List<CandidateDTO> convertCandidatesToDTOs(List<Candidate> candidates) {
        return candidates.stream()
                .map(candidate -> new CandidateDTO(
                        candidate.getId(),
                        candidate.getName(),
                        candidate.getDescription(),
                        candidate.getImage(),
                        candidate.getContactInformation(),
                        candidate.getBallotCount()
                ))
                .collect(Collectors.toList());
    }

    private List<Candidate> convertDTOsToCandidates(List<CandidateDTO> candidateDTOs) {
        return candidateDTOs.stream()
                .map(dto -> {
                    Candidate candidate = new Candidate();
                    candidate.setId(dto.getId());
                    candidate.setName(dto.getName());
                    candidate.setDescription(dto.getDescription());
                    candidate.setImage(dto.getImageUrl());
                    candidate.setContactInformation(dto.getContactInformation());
                    candidate.setBallotCount(dto.getBallotCount());
                    return candidate;
                })
                .collect(Collectors.toList());
    }
}
