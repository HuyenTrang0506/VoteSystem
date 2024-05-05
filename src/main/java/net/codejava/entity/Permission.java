package net.codejava.entity;

import lombok.*;

import javax.persistence.*;
// dynamic persissions are permissions that are not predefined in the system
// they are created by the admin and assigned to voters in specific elections
// for example, those dynamic permission can have: add voter/ view progress/ manage election
@Entity
@Table(name = "dynamic_persissions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;

    public Permission(String name) {
        this.name = name;
    }

    public Permission(Integer id) {
    this.id = id;
    }


}
