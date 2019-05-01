package com.footballstatistics.backend.service;

import com.footballstatistics.backend.model.Match;

import java.util.List;

public interface MatchService {
    public List<Match> getAllMatches();

    public Match getMatch(String homeTeam, String awayTeam);
}
