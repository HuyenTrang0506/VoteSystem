package net.codejava.service;

import net.codejava.dto.ElectionDTO;
import net.codejava.entity.Election;
import net.codejava.repository.ElectionRepository;
import net.codejava.service.service.ElectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.config.ModelMapperConfig;


import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;


@Service
@Transactional
public class ElectionServiceImpl implements ElectionService {
    @Autowired
    private ElectionRepository electionRepository;
    @Autowired
    private ModelMapperConfig modelMapper;
    @Override
    public Election save(ElectionDTO electionDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Election election = modelMapper.map(electionDTO, Election.class);
        return electionRepository.save(election);
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
    public List<Election> getAllElection() {
        return electionRepository.findAll();
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
}
