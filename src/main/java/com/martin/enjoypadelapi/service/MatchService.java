package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;

import java.util.List;

public interface MatchService {
    List<Match> findAll();
    Match findById(long id) throws MatchNotFoundException;
    Match addMatch(Match match);
    Match deleteMatch(long id) throws MatchNotFoundException;
    Match modifyMatch(long id, Match match) throws MatchNotFoundException;
}
