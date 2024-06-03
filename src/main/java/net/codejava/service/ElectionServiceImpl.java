package net.codejava.service;

import net.codejava.dto.CandidateDTO;
import net.codejava.dto.ElectionDTO;
import net.codejava.entity.Candidate;
import net.codejava.entity.Election;
import net.codejava.repository.ElectionRepository;
import net.codejava.service.service_interface.CandidateService;
import net.codejava.service.service_interface.ElectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.config.ModelMapperConfig;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import javax.transaction.Transactional;


@Service
@Transactional
public class ElectionServiceImpl implements ElectionService {
    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private CandidateService candidateService;

    @Override
    public Election save(Election election) {

        return electionRepository.save(election);
    }
    @Override
    public ElectionDTO save(ElectionDTO electionDTO) {
        Election election = convertToEntity(electionDTO);
        Election savedElection = electionRepository.save(election);
        return convertToDTO(savedElection);

    }


    @Override
    public Election findElectionById(Long id) {
        Optional<Election> q = electionRepository.findById(id);
        Election election = null;
        if (q.isPresent()) {
            election = q.get();
        }
        return election;

    }

    @Override
    public Election update(Election election) {

        return null;
    }

    @Override
    public Boolean delete(Election election) {
        try {
            electionRepository.delete(election);
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return false; // Return false if deletion fails
        }
    }

    @Override
    public List<ElectionDTO> getAllElection() {
        List<Election> elections = electionRepository.findAll();
        return elections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean delete(Long id) {
        try {
            electionRepository.deleteById(id);
            return true; // Return true if deletion is successful
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return false; // Return false if deletion fails
        }
    }
    private ElectionDTO convertToDTO(Election election) {
        ElectionDTO electionDTO = new ElectionDTO();
        electionDTO.setId(election.getId());
        electionDTO.setTitle(election.getTitle());
        electionDTO.setDescription(election.getDescription());
        electionDTO.setStartTime(election.getStartTime());
        electionDTO.setEndTime(election.getEndTime());
        electionDTO.setListVoterIds(election.getVoterIds());
        electionDTO.setListCandidates(convertCandidatesToDTOs(election.getListCandidates()));
        electionDTO.setListBallotIds(election.getBallotIds());
        electionDTO.setResultId(election.getResultId());
        return electionDTO;
    }
    private Election convertToEntity(ElectionDTO electionDTO) {
        Election election = new Election();
        election.setId(electionDTO.getId());
        election.setTitle(electionDTO.getTitle());
        election.setDescription(electionDTO.getDescription());
        election.setStartTime(electionDTO.getStartTime());
        election.setEndTime(electionDTO.getEndTime());
        System.out.println("list candidate: "+electionDTO.getListCandidates().get(1).getName());
        election.setListCandidates(convertDTOsToCandidates(electionDTO.getListCandidates()));
        return election;

    }
    private List<CandidateDTO> convertCandidatesToDTOs(List<Candidate> candidates) {
        return candidates.stream()
                .map(candidate -> new CandidateDTO(
                        candidate.getId(),
                        candidate.getName(),
                        candidate.getDescription(),
                        candidate.getImage(),
                        candidate.getContactInformation()
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
                    return candidate;
                })
                .collect(Collectors.toList());
    }
}
