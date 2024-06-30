package net.codejava.repository;

import net.codejava.entity.Election;
import net.codejava.entity.User;
import net.codejava.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    List<Voter> findByUserId(Long userId);
    @Query("SELECT v FROM Voter v WHERE v.user= ?1 AND v.election = ?2")
    Voter findByUserAndElection(User user, Election election);
}
