package com.footballstatistics.backend.rest;

import com.footballstatistics.backend.model.MatchInTicket;
import com.footballstatistics.backend.service.impl.MatchInTicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class MatchInTicketController {

    private MatchInTicketServiceImpl matchInTicketService;

    public MatchInTicketController(){

    }

    @Autowired
    public MatchInTicketController(MatchInTicketServiceImpl matchInTicketService){
        this.matchInTicketService = matchInTicketService;
    }

    @RequestMapping(value = "/my-matches", method = RequestMethod.GET)
    public List<MatchInTicket> listMatches(){
        return matchInTicketService.getAllMatches();
    }

    @RequestMapping(value = "/my-matches/{id}", method = RequestMethod.GET)
    public MatchInTicket getMatch(@PathVariable("id") String id, CreatedMatchInTicket createdMatchInTicket){
        return matchInTicketService.updateMatch(Long.parseLong(id), createdMatchInTicket.tip);
    }

    @RequestMapping(value = "/my-matches", method = RequestMethod.POST)
    public MatchInTicket addMatch(@RequestBody CreatedMatchInTicket createdMatchInTicket){
        return matchInTicketService.create(createdMatchInTicket);
    }

    @RequestMapping(value = "/my-matches/{id}", method = RequestMethod.PATCH)
    public MatchInTicket updateMatch(@PathVariable("id") String id,@RequestBody CreatedMatchInTicket createdMatchInTicket){
        return matchInTicketService.updateMatch(Long.parseLong(id), createdMatchInTicket.tip);
    }

    @RequestMapping(value = "/my-matches/{id}" , method = RequestMethod.DELETE)
    public void removeMatch(@PathVariable("id") String id){
        matchInTicketService.removeMatch(Long.parseLong(id));
    }
}
