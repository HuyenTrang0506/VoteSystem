package net.codejava.service.service_interface;

import net.codejava.dto.ElectionDTO;
import net.codejava.entity.Election;

import java.util.List;

public interface ElectionService {

    Election save(Election election);
    ElectionDTO save(ElectionDTO electionDTO);

    Election findElectionById(Long id);
    Election update(Election election);
    Boolean delete(Election election);

    List<ElectionDTO> getAllElection();

    Boolean delete(Long id);

}
