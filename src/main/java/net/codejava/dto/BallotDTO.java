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

}
