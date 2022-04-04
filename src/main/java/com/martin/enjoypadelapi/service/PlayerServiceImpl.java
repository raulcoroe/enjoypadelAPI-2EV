package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import com.martin.enjoypadelapi.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> findAll() {
        List<Player> players = playerRepository.findAll();
        return players;
    }

    @Override
    public Player findById(long id) throws PlayerNotFoundException {
        Player player = playerRepository.findById(id)
                .orElseThrow(PlayerNotFoundException::new);
        return player;
    }

    @Override
    public Player addPlayer(Player newPlayer) {
        Player player = playerRepository.save(newPlayer);
        return player;
    }

    @Override
    public Player deletePlayer(long id) throws PlayerNotFoundException {
        Player player = playerRepository.findById(id)
                .orElseThrow(PlayerNotFoundException::new);
        playerRepository.delete(player);
        return player;
    }

    @Override
    public Player modifyPlayer(long id, Player newPlayer) throws PlayerNotFoundException {
        playerRepository.findById(id)
                .orElseThrow(PlayerNotFoundException::new);
        newPlayer.setId(id);
        return playerRepository.save(newPlayer);
    }
}
