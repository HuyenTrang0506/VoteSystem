package net.codejava.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//join table between user and election
@Entity
@Table(name = "voters")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    //key
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    //key
    @ManyToOne
    @JoinColumn(name = "election_id", nullable = false)
    private Election election;

    @OneToMany
    @JoinColumn(name = "voter_id")
    private Set<Permission> permissions = new HashSet<>();




}
