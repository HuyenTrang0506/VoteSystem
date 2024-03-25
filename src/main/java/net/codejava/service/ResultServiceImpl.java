package net.codejava.service;

import net.codejava.dto.ResultDTO;
import net.codejava.entity.Candidate;
import net.codejava.entity.Election;
import net.codejava.entity.Result;
import net.codejava.repository.ResultRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.service.ResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {
@Autowired
private ResultRepository resultRepository;
    @Override
    public Result save(ResultDTO resultDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Result result = modelMapper.map(resultDTO, Result.class);
        return resultRepository.save(result);
    }

    @Override
    public Result findResultById(Long id) {
        Optional<Result> q = resultRepository.findById(id);
        Result result = null;
        if (q.isPresent()) {
            result = q.get();
        }
        return result;
    }

    @Override
    public Result findResultByElectionId(Long id) {
        return findResultByElectionId(id);
    }

    @Override
    public Result update(Result result) {
        return null;
    }

    @Override
    public boolean deleteResultById(Long id) {
        try {
            resultRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return false; // Return false if deletion fails
        }
    }

    @Override
    public boolean delete(Result result) {
        try {
            resultRepository.delete(result);
            return true;
        } catch (Exception e) {
            // Handle any exceptions or errors here
            return false; // Return false if deletion fails
        }
    }
}
