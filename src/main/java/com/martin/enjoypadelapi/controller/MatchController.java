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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MatchController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private MatchService matchService;

    @GetMapping("/matches")
    public ResponseEntity<List<Match>> findAll() {
        logger.info("Inicio findAll(matches)");
        List<Match> matches = matchService.findAll();
        logger.info("Final findAll(matches)");
        return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @GetMapping("/match/{id}")
    public ResponseEntity<Match> findById(@PathVariable long id) throws MatchNotFoundException {
        logger.info("Inicio findById(match(");
        Match match = matchService.findById(id);
        logger.info("Final findById(match)");
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @PostMapping("/matches")
    public ResponseEntity<Match> addMatch(@Valid @RequestBody MatchDTO matchDto) throws PlayerNotFoundException, CenterNotFoundException {
        logger.info("Inicio addMatch");
        Match match = matchService.addMatch(matchDto);
        logger.info("Final addMatch");
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }

    @PutMapping("/match/{id}")
    public ResponseEntity<Match> modifyMatch(@PathVariable long id, @Valid @RequestBody MatchDTO matchDto) throws MatchNotFoundException, PlayerNotFoundException, CenterNotFoundException {
        logger.info("Inicio modifyMatch");
        Match newMatch = matchService.modifyMatch(id, matchDto);
        logger.info("Final modifyMatch");
        return new ResponseEntity<>(newMatch, HttpStatus.OK);
    }

    @PatchMapping("/match/{id}")
    public ResponseEntity<Match> partialMatchModification(@PathVariable long id,@Valid @RequestBody Map<Object, Object> fields) throws MatchNotFoundException {
        logger.info("Inicio partialMatchModification");
        Match match = matchService.partialMatchModification(id, fields);
        logger.info("Final partialMatchModification");
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @DeleteMapping("/match/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable long id)throws MatchNotFoundException {
        logger.info("Inicio deleteMatch");
        matchService.deleteMatch(id);
        logger.info("Final deleteMatch");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMatchNotFoundException(MatchNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.error(mnfe.getMessage(), mnfe);
        logger.error(Arrays.toString(mnfe.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFoundException(PlayerNotFoundException pnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        logger.error(Arrays.toString(pnfe.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCenterNotFoundException(CenterNotFoundException cenfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cenfe.getMessage());
        logger.error(cenfe.getMessage());
        logger.error(Arrays.toString(cenfe.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("3", "Internal server error");
        logger.error(exception.getMessage(), exception);
        logger.error(Arrays.toString(exception.getStackTrace()));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logger.error(ex.getMessage(), ex);
        logger.error(Arrays.toString(ex.getStackTrace()));
        return errors;
    }
}
