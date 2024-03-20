package net.codejava.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDTO {
    private Long id;
    private Long electionId;
    private String name;
    private String description;
    private String image;
    private String contactInformation;

   

}
