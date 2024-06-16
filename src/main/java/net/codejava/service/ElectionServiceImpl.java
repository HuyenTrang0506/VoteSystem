package net.codejava.service;

import net.codejava.dto.CandidateDTO;
import net.codejava.dto.ElectionDTO;
import net.codejava.entity.Candidate;
import net.codejava.entity.Election;
import net.codejava.entity.User;
import net.codejava.repository.ElectionRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.service_interface.CandidateService;
import net.codejava.service.service_interface.ElectionService;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import net.codejava.config.ModelMapperConfig;


import java.util.*;
import java.util.stream.Collectors;


import javax.transaction.Transactional;


@Service
@Transactional
public class ElectionServiceImpl implements ElectionService {

    private final ElectionRepository electionRepository;
    private final UserRepository userRepository;

    @Autowired
    public ElectionServiceImpl(ElectionRepository electionRepository,UserRepository userRepository) {
        this.electionRepository = electionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Election save(Election election) {

        return electionRepository.save(election);
    }
    @CacheEvict(value = "elections", allEntries = true)

    @Override
    public ElectionDTO save(ElectionDTO electionDTO) {
        Election election = convertToEntity(electionDTO);
        Election savedElection = electionRepository.save(election);



//        for (Voter voter : savedElection.getListVoters()) {
//            voter.setElection(savedElection);
//
//
//        }
        return convertToDTO(savedElection);

    }

    @Cacheable(value = "elections", key = "#id")

    @Override
    public ElectionDTO findElectionById(Long id) {
        Optional<Election> q = electionRepository.findById(id);
        ElectionDTO electionDto = null;
        if (q.isPresent()) {
            electionDto = convertToDTO(q.get());
        }
        return electionDto;

    }


    @Override
    public List<ElectionDTO> getAllElection() {
        List<Election> elections = electionRepository.findAll();
        return elections.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "elections", allEntries = true)
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

    @CacheEvict(value = "elections", allEntries = true)
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
    @Override
    public Election update(Election election) {

        return null;
    }
    private ElectionDTO convertToDTO(Election election) {
        ElectionDTO electionDTO = new ElectionDTO();
        electionDTO.setId(election.getId());
        electionDTO.setTitle(election.getTitle());
        electionDTO.setDescription(election.getDescription());
        electionDTO.setStartTime(election.getStartTime());
        electionDTO.setEndTime(election.getEndTime());
        electionDTO.setListCandidates(convertCandidatesToDTOs(election.getListCandidates()));
        System.out.println("list user: " + election.getUsers().size());
        electionDTO.setUserIds(election.getUserIds());
        return electionDTO;
    }


    private Election convertToEntity(ElectionDTO electionDTO) {
        Election election = new Election();
        election.setId(electionDTO.getId());
        election.setTitle(electionDTO.getTitle());
        election.setDescription(electionDTO.getDescription());
        election.setStartTime(electionDTO.getStartTime());
        election.setEndTime(electionDTO.getEndTime());
        System.out.println("list candidate: " + electionDTO.getListCandidates().get(1).getName());
        election.setListCandidates(convertDTOsToCandidates(electionDTO.getListCandidates()));
        List<User> users = new ArrayList<>();
        List<Long> userIds = electionDTO.getUserIds();
        if (userIds != null) {
        for(Long id : electionDTO.getUserIds()){
            System.out.println("userid size: " + electionDTO.getUserIds().size());
            Optional<User> userOptional = userRepository.findById(id);
            if(userOptional.isPresent()){
                users.add(userOptional.get());
            }
            election.setUsers(users);
            System.out.println("user: " + users.size()+"userId"+ electionDTO.getUserIds().get(0));
        }}
        return election;

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
