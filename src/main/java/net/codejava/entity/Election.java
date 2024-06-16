package net.codejava.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;


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


//    @JsonIgnore
//    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Voter> listVoters;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "election_id")
    private List<Candidate> listCandidates;

    @ManyToMany
    @JoinTable(
            name = "election_users",
            joinColumns = @JoinColumn(name = "election_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users ;
//
//    @JsonIgnore
//    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Ballot> listBallots;
    @JsonIgnore
    @OneToOne(mappedBy = "election", cascade = CascadeType.ALL, orphanRemoval = true)
    private Result result;

    //constructors


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

    private void init() {
        this.users = new ArrayList<>();
        this.listCandidates = new ArrayList<>();

        result = null;
    }

    //check if result is null
    public Long getResultId() {
        if (result == null) {
            return null;
        } else {
            return result.getId();
        }

    }

//    public List<Long> getVoterIds() {
//        List<Long> voterIds = new ArrayList<>();
//        for (Voter voter : listVoters) {
//            voterIds.add(voter.getId());
//        }
//        return voterIds;
//    }
    public List<Long> getUserIds() {
        List<Long> userIds = new ArrayList<>();
        for (User user : users) {
            userIds.add(user.getId());
        }
        return userIds;
    }

    public List<Long> getCandidateIds() {
        List<Long> candidateIds = new ArrayList<>();
        for (Candidate candidate : listCandidates) {
            candidateIds.add(candidate.getId());
        }
        return candidateIds;
    }

}

