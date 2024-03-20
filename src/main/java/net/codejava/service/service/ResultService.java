package net.codejava.service.service;

import net.codejava.entity.Result;

public interface ResultService {
    Result save(Result result);

    Result findResultById(Long id);
    Result update(Result result);
    void deleteResultById(Long id);






}
