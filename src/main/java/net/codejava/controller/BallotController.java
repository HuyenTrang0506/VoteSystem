package net.codejava.controller;

import net.codejava.service.service.BallotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ballots")
public class BallotController {
    @Autowired
    private BallotService ballotService;
}
