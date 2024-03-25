package net.codejava.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "dynamic_persissions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DynamicPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
}
