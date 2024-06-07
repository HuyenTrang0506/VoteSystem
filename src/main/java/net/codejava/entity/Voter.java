//package net.codejava.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Table(name = "voters",
//        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "election_id"})})
//@Setter
//@Getter
//@AllArgsConstructor
//@EqualsAndHashCode
//public class Voter {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "election_id", nullable = false)
//    private Election election;
//
////    @OneToMany
////    @JoinTable(
////            name = "voter_permissions",
////            joinColumns = @JoinColumn(name = "voter_id"),
////            inverseJoinColumns = @JoinColumn(name = "permission_id")
////    )
////    private Set<Permission> permissions = new HashSet<>();
//
////    public void addPermission(Permission permission) {
////        this.permissions.add(permission);
////    }
////
////    public void removePermission(Permission permission) {
////        this.permissions.remove(permission);
////    }
//
//    public Voter() {
//        this.user = null;
//        this.election = null;
//    }
//
//    public Voter(User user) {
//        this.user = user;
//        this.election = null;
//    }
//
//
//
//    public Voter(User user, Election election) {
//        this.user = user;
//        this.election = election;
//
//    }
//
//}
