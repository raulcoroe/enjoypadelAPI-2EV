package com.martin.enjoypadelapi.controller;

import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.exception.ErrorResponse;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import com.martin.enjoypadelapi.service.PlayerService;
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
public class PlayerController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public ResponseEntity<List<Player>> findAll(@RequestParam(name = "availability", defaultValue = "0") boolean availability) {
        logger.info("Inicio getPlayers");
        List<Player> players;
        if (availability) {
             players = playerService.findAll(availability);
        } else {
            players = playerService.findAll();
        }
        logger.info("Final getPlayers");
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Player> findById(@PathVariable long id) throws PlayerNotFoundException {
        logger.info("Inicio findById");
        Player player = playerService.findById(id);
        logger.info("Final findById");
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping("/players")
    public ResponseEntity<Player> addPlayer(@Valid @RequestBody Player newPlayer) {
        logger.info("Inicio addPlayer");
        Player player = playerService.addPlayer(newPlayer);
        logger.info("Final addPlayer");
        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @PutMapping("/player/{id}")
    public ResponseEntity<Player> modifyPlayer(@PathVariable long id, @Valid @RequestBody Player newPlayer) throws PlayerNotFoundException {
        logger.info("Inicio modifyPlayer");
        Player player = playerService.modifyPlayer(id, newPlayer);
        logger.info("Final modifyPlayer");
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @DeleteMapping("/player/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable long id) throws PlayerNotFoundException {
        logger.info("Inicio deletePlayer");
        playerService.deletePlayer(id);
        logger.info("Final deletePlayer");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/player/{id}")
    public ResponseEntity<Player> partialPlayerModification(@PathVariable long id, @Valid @RequestBody Map<Object, Object> fields) throws PlayerNotFoundException {
        logger.info("Inicio partialPlayerModification");
        Player player = playerService.partialPlayerModification(id, fields);
        logger.info("Final partialPlayerModification");
        return new ResponseEntity<>(player, HttpStatus.OK);
    }


    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFoundException(PlayerNotFoundException pnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        logger.error(Arrays.toString(pnfe.getStackTrace()));
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
