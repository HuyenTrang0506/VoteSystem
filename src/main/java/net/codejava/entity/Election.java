package net.codejava.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import lombok.*;


@Entity
@Table(name = "elections")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


    //now join table is voter, fix my code
    @OneToMany(mappedBy = "election")
    private List<Voter> listVoters;

    //fix this code bellow
    @OneToMany(mappedBy = "election")
    private List<Candidate> listCandidates;

    @OneToMany(mappedBy = "election")
    private List<Ballot> listBallots;
    @OneToOne(mappedBy = "election")
    private Result result;


}

