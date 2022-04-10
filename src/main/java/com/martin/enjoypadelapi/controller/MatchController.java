package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.dto.MatchDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.ErrorResponse;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
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
    public void addMatch(@RequestBody MatchDTO matchDto) throws PlayerNotFoundException, CenterNotFoundException {
        logger.info("Inicio addMatch");
        matchService.addMatch(matchDto);
        logger.info("Final addMatch");
    }

    @PutMapping("/match/{id}")
    public Match modifyMatch(@PathVariable long id, @RequestBody MatchDTO matchDto) throws MatchNotFoundException, PlayerNotFoundException, CenterNotFoundException {
        logger.info("Inicio modifyMatch");
        Match newMatch = matchService.modifyMatch(id, matchDto);
        logger.info("Final modifyMatch");
        return newMatch;
    }

    @DeleteMapping("/match/{id}")
    public void deleteMatch(@PathVariable long id)throws MatchNotFoundException {
        logger.info("Inicio deleteMatch");
        matchService.deleteMatch(id);
        logger.info("Final deleteMatch");
    }


    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMatchNotFoundException(MatchNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.error(mnfe.getMessage(), mnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFoundException(PlayerNotFoundException pnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCenterNotFoundException(CenterNotFoundException cenfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cenfe.getMessage());
        logger.error(cenfe.getMessage(), cenfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
