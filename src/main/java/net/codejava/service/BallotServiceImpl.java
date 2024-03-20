package net.codejava.service;

import net.codejava.dto.BallotDTO;
import net.codejava.entity.Ballot;
import net.codejava.entity.Election;
import net.codejava.repository.BallotRepository;
import net.codejava.service.service.BallotService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BallotServiceImpl implements BallotService {
    @Autowired
   private BallotRepository ballotRepository;
    @Override
    public Ballot save(BallotDTO ballotDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Ballot ballot= modelMapper.map(ballotDTO, Ballot.class);
        return ballotRepository.save(ballot);
    }

    @Override
    public Ballot findBallotById(Long id) {
        Optional<Ballot> q = ballotRepository.findById(id);
        Ballot ballot = null;
        if (q.isPresent()) {
            ballot = q.get();
        }
        return ballot;
    }

    @Override
    public Ballot update(Ballot ballot) {
        return null;
    }

    @Override
    public boolean delete(Ballot ballot) {
        try {
            ballotRepository.delete(ballot);
            return true;
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return false; // Return false if deletion fails
        }
    }

    @Override
    public boolean deleteBallotById(Long id) {
        try {
            ballotRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return false; // Return false if deletion fails
        }
    }
}
