package net.codejava.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.codejava.entity.Candidate;
import net.codejava.entity.Election;
import net.codejava.entity.User;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BallotDTO {
    private Long id;
    private Long electionId;
    private Long userId;
    private Long candidateId;
    private boolean isBlank;

    // Constructors
    public BallotDTO() {
    }

    public BallotDTO(Long id, Long electionId, Long userId, Long candidateId, boolean isBlank) {
        this.id = id;
        this.electionId = electionId;
        this.userId = userId;
        this.candidateId = candidateId;
        this.isBlank = isBlank;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public boolean isBlank() {
        return isBlank;
    }

    public void setBlank(boolean blank) {
        isBlank = blank;
    }
}
