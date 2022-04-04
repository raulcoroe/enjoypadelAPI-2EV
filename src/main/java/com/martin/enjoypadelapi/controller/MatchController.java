package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.exception.ErrorResponse;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MatchController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private MatchService matchService;

    @GetMapping("/matches")
    public List<Match> findAll() {
        logger.info("Inicio findAll(matches)");
        List<Match> matches = matchService.findAll();
        logger.info("Final findAll(matches)");
        return matches;
    }

    @GetMapping("/match/{id}")
    public Match findById(@PathVariable long id) throws MatchNotFoundException {
        logger.info("Inicio findById(match(");
        Match match = matchService.findById(id);
        logger.info("Final findById(match)");
        return match;
    }

    @PostMapping("/matches")
    public Match addMatch(@RequestBody Match match)  {
        logger.info("Inicio addMatch");
        Match newMatch = matchService.addMatch(match);
        logger.info("Final addMatch");
        return newMatch;
    }

    @PutMapping("/match/{id}")
    public Match modifyMatch(@PathVariable long id, @RequestBody Match match) throws MatchNotFoundException {
        logger.info("Inicio modifyMatch");
        Match newMatch = matchService.modifyMatch(id, match);
        logger.info("Final modifyMatch");
        return newMatch;
    }

    @DeleteMapping("/match/{id}")
    public Match deleteMatch(@PathVariable long id)throws MatchNotFoundException {
        logger.info("Inicio deleteMatch");
        Match match = matchService.deleteMatch(id);
        logger.info("Final deleteMatch");
        return match;
    }


    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMatchNotFoundException(MatchNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.error(mnfe.getMessage(), mnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
