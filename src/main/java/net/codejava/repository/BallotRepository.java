package net.codejava.repository;

import net.codejava.entity.Ballot;
import net.codejava.entity.Election;
import net.codejava.entity.Result;
import net.codejava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallotRepository extends JpaRepository<Ballot, Long> {


    List<Ballot> findAllByElection(Election election);
    List<Ballot> findAllByElectionId(Long electionId);

}
