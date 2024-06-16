package net.codejava.controller;

import net.codejava.entity.Involce;
import net.codejava.repository.InvolceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/involces")
public class InvolceController {
    @Autowired
    InvolceRepository involceRepository;
    @PostMapping
    public ResponseEntity<Involce> saveInvolce(@RequestBody Involce involce) {
       involceRepository.save(involce);
       return ResponseEntity.ok(involce);
    }
}
