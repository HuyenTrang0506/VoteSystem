package net.codejava.service.service_interface;

import net.codejava.dto.ResultDTO;
import net.codejava.entity.Result;

public interface ResultService {
    Result save(ResultDTO resultDTO);

    Result findResultById(Long id);
    Result findResultByElectionId(Long id);
    Result update(Result result);
    boolean deleteResultById(Long id);
    boolean delete(Result result);






}
