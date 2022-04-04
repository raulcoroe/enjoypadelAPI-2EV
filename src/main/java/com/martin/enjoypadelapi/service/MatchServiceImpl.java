package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.repository.MatchRepository;
import com.martin.enjoypadelapi.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements  MatchService{

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

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
    public Match addMatch(Match match)  {
        matchRepository.save(match);
        return match;
    }

    @Override
    public Match deleteMatch(long id) throws MatchNotFoundException {
        Match match = matchRepository.findById(id)
                .orElseThrow(()-> new MatchNotFoundException());
        matchRepository.delete(match);
        return match;
    }

    @Override
    public Match modifyMatch(long id, Match match) throws MatchNotFoundException {
        matchRepository.findById(id)
                .orElseThrow(MatchNotFoundException::new);
        match.setId(id);
        matchRepository.save(match);
        return match;
    }
}
