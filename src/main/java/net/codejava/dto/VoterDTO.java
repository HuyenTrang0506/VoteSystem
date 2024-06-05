package net.codejava.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
@Getter
@Setter
public class VoterDTO {
    private Long id;
    private Long electionId;
    private Long userId;


    private Set<String> permissions;
    // Constructors, getters, setters
    public VoterDTO() {
    }

    public VoterDTO(Long id, Long electionId, Long userId,  Set<String> permissions) {
        this.id = id;
        this.electionId = electionId;
        this.userId = userId;

        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public Long getElectionId() {
        return electionId;
    }

    public Long getUserId() {
        return userId;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
