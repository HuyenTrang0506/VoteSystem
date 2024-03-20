package net.codejava.entity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "election_id")
    private Election election;

    @Column(name = "ballot_count")
    private int ballotCount;

    @Column(name = "blank_ballot_count")
    private int blankBallotCount;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Candidate winner;


}
