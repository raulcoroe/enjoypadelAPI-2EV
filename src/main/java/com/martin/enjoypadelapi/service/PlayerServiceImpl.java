package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import com.martin.enjoypadelapi.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Base64;
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
    public void addPlayer(Player newPlayer) {
        byte[] encoded = Base64Utils.encode(newPlayer.getImage());
        newPlayer.setImage(null);
        playerRepository.save(newPlayer);
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
        newPlayer.setImage(null);
        return playerRepository.save(newPlayer);
    }

    @Override
    public Player partialPlayerModification(long id, Map<Object, Object> fields) throws PlayerNotFoundException {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException());
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Player.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, player, v);
        });
        Player playerModified = modifyPlayer(id, player);
        return playerModified;
    }
    @Override
    public List<Player> findAll(boolean availability) {
        return playerRepository.findAll(availability);
    }
}
