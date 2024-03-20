package net.codejava.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ballots")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Ballot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ballot_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "election_id")

    private Election election;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "is_blank")
    private boolean isBlank;

}
