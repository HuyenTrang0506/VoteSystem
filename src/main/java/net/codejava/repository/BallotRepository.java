package net.codejava.repository;

import net.codejava.entity.Ballot;
import net.codejava.entity.Result;
import net.codejava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, Long> {
    Result findByElectionId(Long electionId);
}
