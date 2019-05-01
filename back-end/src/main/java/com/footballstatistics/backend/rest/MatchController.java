package com.footballstatistics.backend.rest;

import com.footballstatistics.backend.model.Match;
import com.footballstatistics.backend.service.impl.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class MatchController {


    private MatchServiceImpl matchService;

    @Autowired
    public MatchController(MatchServiceImpl matchService){
        this.matchService = matchService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Match> listMatches(){
        return this.matchService.getAllMatches();
    }

    @RequestMapping(value = "/compare-teams/{homeTeam}/vs/{awayTeam}",method = RequestMethod.GET)
    public Match getMatch(@PathVariable("homeTeam") String homeTeam, @PathVariable("awayTeam") String awayTeam){
        return this.matchService.getMatch(homeTeam, awayTeam);
    }

}
