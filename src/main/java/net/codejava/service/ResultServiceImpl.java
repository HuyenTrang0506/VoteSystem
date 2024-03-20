package net.codejava.service;

import net.codejava.entity.Result;
import net.codejava.repository.ResultRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ResultServiceImpl implements ResultService {
@Autowired
private ResultRepository resultRepository;
    @Override
    public Result save(Result result) {
        return null;
    }

    @Override
    public Result findResultById(Long id) {
        return null;
    }

    @Override
    public Result update(Result result) {
        return null;
    }

    @Override
    public void deleteResultById(Long id) {

    }
}
