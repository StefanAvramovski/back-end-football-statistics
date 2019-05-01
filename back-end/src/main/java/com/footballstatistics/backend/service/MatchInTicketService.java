package com.footballstatistics.backend.service;

import com.footballstatistics.backend.model.MatchInTicket;
import com.footballstatistics.backend.rest.CreatedMatchInTicket;

import java.util.List;

public interface MatchInTicketService {

    List<MatchInTicket> getAllMatches();

    MatchInTicket getMatchById(Long id);

    MatchInTicket create(CreatedMatchInTicket createdMatchInTicket);

    MatchInTicket updateMatch(Long id, String tip);

    void removeMatch(Long id);
}
