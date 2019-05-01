package com.footballstatistics.backend.service.impl;

import com.footballstatistics.backend.model.MatchInTicket;
import com.footballstatistics.backend.model.exception.InvalidIdentifier;
import com.footballstatistics.backend.persistance.MatchInTicketRepository;
import com.footballstatistics.backend.rest.CreatedMatchInTicket;
import com.footballstatistics.backend.service.MatchInTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchInTicketServiceImpl implements MatchInTicketService {

    private MatchInTicketRepository matchInTicketRepository;

    @Autowired
    public MatchInTicketServiceImpl(MatchInTicketRepository matchInTicketRepository){
        this.matchInTicketRepository = matchInTicketRepository;
    }

    @Override
    public List<MatchInTicket> getAllMatches(){
        return matchInTicketRepository.findAll().collect(Collectors.toList());
    }

    @Override
    public MatchInTicket getMatchById(Long id){
            return matchInTicketRepository.findById(id).orElseThrow(InvalidIdentifier::new);
    }

    @Override
    public MatchInTicket create(CreatedMatchInTicket createdMatchInTicket){
        MatchInTicket matchInTicket = new MatchInTicket();

        matchInTicket.homeTeam = createdMatchInTicket.homeTeam;
        matchInTicket.awayTeam = createdMatchInTicket.awayTeam;
        matchInTicket.tip = createdMatchInTicket.tip;

        return matchInTicketRepository.save(matchInTicket);
    }

    @Override
    public MatchInTicket updateMatch(Long id, String tip){
        MatchInTicket matchInTicket = matchInTicketRepository.findById(id).orElseThrow(InvalidIdentifier::new);

        matchInTicket.tip = tip == null ? matchInTicket.tip: tip;

        return matchInTicketRepository.save(matchInTicket);
    }

    @Override
    @Transactional
    public void removeMatch(Long id){
        matchInTicketRepository.deleteById(id);
    }
}
