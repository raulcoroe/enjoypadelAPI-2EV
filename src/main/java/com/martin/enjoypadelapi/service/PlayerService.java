package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;

import java.util.List;
import java.util.Map;

public interface PlayerService {
    List<Player> findAll();
    Player findById(long id) throws PlayerNotFoundException;
    void addPlayer(Player newPlayer);
    Player deletePlayer(long id) throws PlayerNotFoundException;
    Player modifyPlayer(long id, Player newPlayer) throws PlayerNotFoundException;
    Player partialPlayerModification(long id, Map<Object, Object> fields) throws PlayerNotFoundException;
    List<Player> findAll(boolean availability);
}
