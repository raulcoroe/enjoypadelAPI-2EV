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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {

    private final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public List<Player> findAll(@RequestParam(name = "availability", defaultValue = "0") boolean availability) {
        logger.info("Inicio getPlayers");
        List<Player> players;
        if (availability) {
             players = playerService.findAll(availability);
        } else {
            players = playerService.findAll();
        }
        logger.info("Final getPlayers");
        return players;
    }


    @GetMapping("/player/{id}")
    public Player findById(@PathVariable long id) throws PlayerNotFoundException {
        logger.info("Inicio findById");
        Player player = playerService.findById(id);
        logger.info("Final findById");
        return player;
    }

    @PostMapping("/players")
    public void addPlayer(@RequestBody Player newPlayer) {
        logger.info("Inicio addPlayer");
        playerService.addPlayer(newPlayer);
        logger.info("Final addPlayer");
    }

    @PutMapping("/player/{id}")
    public Player modifyPlayer(@PathVariable long id, @RequestBody Player newPlayer) throws PlayerNotFoundException {
        logger.info("Inicio modifyPlayer");
        Player player = playerService.modifyPlayer(id, newPlayer);
        logger.info("Final modifyPlayer");
        return player;
    }

    @DeleteMapping("/player/{id}")
    public Player deletePlayer(@PathVariable long id) throws PlayerNotFoundException {
        logger.info("Inicio deletePlayer");
        Player player = playerService.deletePlayer(id);
        logger.info("Final deletePlayer");
        return player;
    }


    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFoundException(PlayerNotFoundException pnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
