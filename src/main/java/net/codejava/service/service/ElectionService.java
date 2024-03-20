package net.codejava.service.service;

import net.codejava.dto.ElectionDTO;
import net.codejava.entity.Election;

import java.util.List;

public interface ElectionService {

    Election save(ElectionDTO electionDTO);

    Election findElectionById(Long id);
    Election update(Election election);
    Boolean delete(Election election);

    List<Election> getAllElection();

    Boolean delete(Long id);
}
