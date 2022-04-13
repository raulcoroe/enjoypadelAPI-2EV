package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.dto.MatchDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;

import java.util.List;
import java.util.Map;

public interface MatchService {
    List<Match> findAll();
    Match findById(long id) throws MatchNotFoundException;
    Match addMatch(MatchDTO matchDTO) throws PlayerNotFoundException, CenterNotFoundException;
    void deleteMatch(long id) throws MatchNotFoundException;
    Match partialMatchModification(long id, Map<Object, Object> fields) throws MatchNotFoundException;
}
