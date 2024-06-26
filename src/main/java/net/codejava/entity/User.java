package net.codejava.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    @Email
    @Length(min = 5, max = 50)
    private String email;

    @Column(nullable = true)
    private String fullname;

    @Column(nullable = false, length = 64)
    @Length(min = 5, max = 64)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] avatarUrl;
    @Column(nullable = false)
    private boolean isPro = false;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    //    @OneToMany(mappedBy = "user")
//    private Set<Voter> voters = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Election> elections = new HashSet<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
    void changePro(){
        this.isPro = !this.isPro;
    }
    public void addRole(Role role) {
        this.roles.add(role);
    }

    public boolean getIsPro() {
        return isPro;
    }
//    public void addVoter(Voter voter) {
//        this.voters.add(voter);
//    }
//    public List<Long> getVoterIds(){
//        List<Long> voterIds = new ArrayList<>();
//        for (Voter voter : voters) {
//            voterIds.add(voter.getId());
//        }
//        return voterIds;
//    }

//    public List<Long> getElectionIdsByVoters() {
//        List<Long> electionIds = new ArrayList<>();
//        for (Voter voter : voters) {
//            electionIds.add(voter.getElection().getId());
//        }
//        return electionIds;
//    }
}
