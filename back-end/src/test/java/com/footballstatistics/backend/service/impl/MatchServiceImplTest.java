package com.footballstatistics.backend.service.impl;

import com.footballstatistics.backend.model.Match;
import com.footballstatistics.backend.service.MatchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class MatchServiceImplTest {

    @MockBean
    private MatchService matchService;

    @MockBean
    private MatchServiceImpl matchServiceImpl;

    private Match match;

    @Before
    public void setUp(){
        match = new Match();
    }

    @Test
    public void getAllMatches() {
        ArrayList<Match> matches = new ArrayList<>();
        Mockito.when(matchService.getAllMatches()).thenReturn(matches);

        assertEquals(matches, matchService.getAllMatches());
    }


    @Test
    public void getMatchNamesTest() {
        String homeTeam = "nameHomeTeam";
        String awayTeam = "nameAwayTeam";
        match = new Match(homeTeam, awayTeam);
        Mockito.when(matchService.getMatch(homeTeam, awayTeam)).thenReturn(match);

        assertEquals(match, matchService.getMatch(homeTeam, awayTeam));
    }

    @Test
    public void getMatchWithoutNamesTest(){
        String homeTeam = "";
        String awayTeam = "";

        matchService.getMatch(homeTeam, awayTeam);
    }

    @Test
    public void getMatchWithoutOneNameTest(){
        String homeTeam = "nameHomeTeam";
        String awayTeam = "";

        matchService.getMatch(homeTeam, awayTeam);
    }

    @Test
    public void getMatchWithoutOneNameTest1(){
        String homeTeam = "";
        String awayTeam = "nameAwayTeam";

        matchService.getMatch(homeTeam, awayTeam);
    }

    @Test
    public void getIntegerArrayTest(){
        ArrayList<String> testStringArray = new ArrayList<>();
        testStringArray.add("1");
        testStringArray.add("2");

        ArrayList<Integer> testIntegerArray = new ArrayList<>();
        testIntegerArray.add(1);
        testIntegerArray.add(2);

        Mockito.when(matchServiceImpl.getIntegerArray(testStringArray)).thenReturn(testIntegerArray);

        assertEquals(testIntegerArray, matchServiceImpl.getIntegerArray(testStringArray));
    }

    @Test
    public void getIntegerArrayTestNull(){
        ArrayList<String> testStringArray = new ArrayList<>();
        matchServiceImpl.getIntegerArray(testStringArray);
    }

}