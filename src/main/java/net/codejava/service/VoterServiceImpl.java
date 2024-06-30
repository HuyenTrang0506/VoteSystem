package net.codejava.service;

import net.codejava.dto.VoterDTO;
import net.codejava.entity.Election;
import net.codejava.entity.Permission;
import net.codejava.entity.User;
import net.codejava.entity.Voter;
import net.codejava.repository.ElectionRepository;
import net.codejava.repository.PermissionRepository;
import net.codejava.repository.UserRepository;
import net.codejava.repository.VoterRepository;
import net.codejava.service.service_interface.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final UserRepository userRepository;
    private final ElectionRepository electionRepository;
    private final PermissionRepository permissionRepository;

    @Autowired
    public VoterServiceImpl(VoterRepository voterRepository, UserRepository userRepository, ElectionRepository electionRepository, PermissionRepository permissionRepository) {
        this.voterRepository = voterRepository;
        this.userRepository = userRepository;
        this.electionRepository = electionRepository;
        this.permissionRepository = permissionRepository;
    }
    @Override
    public List<VoterDTO> getAllVoters() {
        List<Voter> voters = voterRepository.findAll();
        List<VoterDTO> voterDTOS = new ArrayList<>();
        for (Voter voter : voters) {
            VoterDTO voterDTO = new VoterDTO();
            voterDTO.setUserId(voter.getUser().getId());
            voterDTO.setElectionId(voter.getElection().getId());
            Set<String> permissions = new HashSet<>();
            for (Permission permission : voter.getPermissions()) {
                permissions.add(permission.getName());
            }
            voterDTO.setPermissions(permissions);
            voterDTOS.add(voterDTO);
        }
        return voterDTOS;
    }

    @Override
    public List<Voter> getVotersByElectionId(Long electionId) {
        return null;
    }

    @Override
    public List<Voter> getVotersByUserId(Long userId) {
        return voterRepository.findByUserId(userId);
    }

    @Override
    public Voter save(Voter voter) {
        return voterRepository.save(voter);
    }

    @Override
    public List<VoterDTO> saveVoterPermission(Long electionId, List<VoterDTO> listVoterDTO) {
        AtomicReference<Boolean> flag = new AtomicReference<>(false);
        System.out.println("electionId: " + electionId + " listVoterDTO: " + listVoterDTO.get(0).getElectionId());

        Optional<Election> electionOptional = electionRepository.findById(electionId);

        if (electionOptional.isPresent()) {
            Election election = electionOptional.get();

            listVoterDTO.forEach(voterDTO -> {
                Optional<User> userOptional = userRepository.findById(voterDTO.getUserId());

                if (userOptional.isPresent()) {
                    User user = userOptional.get();

                    // Check if the Voter already exists
                    Optional<Voter> existingVoterOptional = Optional.ofNullable(voterRepository.findByUserAndElection(user, election));

                    Voter voter;
                    if (existingVoterOptional.isPresent()) {
                        // Update the existing Voter
                        voter = existingVoterOptional.get();
                    } else {
                        // Create a new Voter
                        voter = new Voter();
                        voter.setUser(user);
                        voter.setElection(election);
                    }

                    Set<Permission> permissions = new HashSet<>();
                    for (String permissionName : voterDTO.getPermissions()) {
                        permissions.add(permissionRepository.findByName(permissionName));
                    }
                    voter.setPermissions(permissions);
                    System.out.println(permissions.toArray()[0].toString());
                    voterDTO.setElectionId(electionId);
                    voterRepository.save(voter);
                    flag.set(true);
                }
            });
        }

        return flag.get() ? listVoterDTO : null;
    }



}

