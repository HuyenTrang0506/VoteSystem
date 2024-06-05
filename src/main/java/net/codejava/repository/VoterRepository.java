package net.codejava.repository;

import net.codejava.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {
    List<Voter> findByUserId(Long userId);

}
