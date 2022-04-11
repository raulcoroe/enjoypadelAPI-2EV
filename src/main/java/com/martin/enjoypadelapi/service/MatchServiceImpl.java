package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.domain.dto.MatchDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import com.martin.enjoypadelapi.repository.CenterRepository;
import com.martin.enjoypadelapi.repository.MatchRepository;
import com.martin.enjoypadelapi.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CenterRepository centerRepository;

    @Override
    public List<Match> findAll() {
        List<Match> matches = matchRepository.findAll();
        return matches;
    }

    @Override
    public Match findById(long id) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
        return match;
    }

    @Override
    public void addMatch(MatchDTO matchDTO) throws PlayerNotFoundException, CenterNotFoundException {
        ModelMapper mapper = new ModelMapper();
        Match match = mapper.map(matchDTO, Match.class);

        Player player1 = playerRepository.findById(matchDTO.getPlayer1()).orElseThrow(PlayerNotFoundException::new);
        Player player2 = playerRepository.findById(matchDTO.getPlayer2()).orElseThrow(PlayerNotFoundException::new);
        Player player3 = playerRepository.findById(matchDTO.getPlayer3()).orElseThrow(PlayerNotFoundException::new);
        Player player4 = playerRepository.findById(matchDTO.getPlayer4()).orElseThrow(PlayerNotFoundException::new);
        Center center = centerRepository.findById(matchDTO.getCenter()).orElseThrow(CenterNotFoundException::new);

        player1.getMatches().add(match);
        player2.getMatches().add(match);
        player3.getMatches().add(match);
        player4.getMatches().add(match);
        match.setCenter(center);
        matchRepository.save(match);
    }

    @Override
    public void deleteMatch(long id) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException());
        matchRepository.delete(match);
    }

    @Override
    public Match modifyMatch(long id, MatchDTO matchDTO) throws MatchNotFoundException, PlayerNotFoundException, CenterNotFoundException {

        ModelMapper mapper = new ModelMapper();
        Match match = mapper.map(matchDTO, Match.class);

        Player player1 = playerRepository.findById(matchDTO.getPlayer1()).orElseThrow(PlayerNotFoundException::new);
        Player player2 = playerRepository.findById(matchDTO.getPlayer2()).orElseThrow(PlayerNotFoundException::new);
        Player player3 = playerRepository.findById(matchDTO.getPlayer3()).orElseThrow(PlayerNotFoundException::new);
        Player player4 = playerRepository.findById(matchDTO.getPlayer4()).orElseThrow(PlayerNotFoundException::new);
        Center center = centerRepository.findById(matchDTO.getCenter()).orElseThrow(CenterNotFoundException::new);

        player1.getMatches().add(match);
        player2.getMatches().add(match);
        player3.getMatches().add(match);
        player4.getMatches().add(match);
        match.setCenter(center);
        match.setId(id);
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match partialMatchModification(long id, Map<Object, Object> fields) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new MatchNotFoundException());
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Match.class, (String) k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, match, v);
        });
        Match matchModified = matchRepository.save(match);
        return matchModified;
    }
}