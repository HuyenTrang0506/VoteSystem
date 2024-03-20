package net.codejava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private Long id;
    private int ballotCount;
    private int blankBallotCount;
    private Long winnerId;
}