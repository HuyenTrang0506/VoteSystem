package net.codejava.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import lombok.*;


@Entity
@Table(name = "elections")
@Setter
@Getter
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



    @OneToMany(mappedBy = "election",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voter> listVoters;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "election_id")
    private List<Candidate> listCandidates;

    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ballot> listBallots;
    @OneToOne(mappedBy = "election", cascade = CascadeType.ALL, orphanRemoval = true)
    private Result result;
    //constructors
    public Election(String title, String description, LocalDateTime startTime, LocalDateTime endTime, List<Voter> listVoters, List<Candidate> listCandidates, List<Ballot> listBallots, Result result) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.listVoters = listVoters;
        this.listCandidates = listCandidates;
        this.listBallots = listBallots;
        this.result = result;
    }
    public Election(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {

        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
         init();
    }
    public Election() {
       init();
    }
    private void init(){
        this.listVoters = new ArrayList<>();
        this.listCandidates = new ArrayList<>();
        this.listBallots = new ArrayList<>();
        result = null;
    }

    public Long getResultId() {
        return result.getId();
    }

    public List<Long> getVoterIds() {
        List<Long> voterIds = new ArrayList<>();
        for (Voter voter : listVoters) {
            voterIds.add(voter.getId());
        }
        return voterIds;
    }

    public List<Long> getCandidateIds() {
        List<Long> candidateIds = new ArrayList<>();
        for (Candidate candidate : listCandidates) {
            candidateIds.add(candidate.getId());
        }
        return candidateIds;
    }

    public List<Long> getBallotIds() {
        List<Long> ballotIds = new ArrayList<>();
        for (Ballot ballot : listBallots) {
            ballotIds.add(ballot.getId());
        }
        return ballotIds;
    }
}

