package net.codejava.entity;

import javax.persistence.*;

import lombok.*;
// this class stores the candidates that are running for an election
@Entity
@Table(name = "candidates")
@Setter
@Getter

@AllArgsConstructor
@EqualsAndHashCode

public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "contact_information")
    private String contactInformation;
    public Candidate (){
        this.name = "";
        this.description = "";
        this.image = "";
        this.contactInformation = "";
    }
    private void output(){
        System.out.println("Candidate: " + name + " " + description + " " + image + " " + contactInformation);
    }
    // Constructors, getters, setters
}
