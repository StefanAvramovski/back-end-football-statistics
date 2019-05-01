package com.footballstatistics.backend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballstatistics.backend.model.Match;
import com.footballstatistics.backend.service.impl.MatchServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
public class MatchControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private MatchServiceImpl matchService;

    @Before
    public void setUp(){
        MatchController matchController = new MatchController(matchService);

        mockMvc = standaloneSetup(matchController).build();
        objectMapper = new ObjectMapper();
    }


    @Test
    public void listMatchesTest() throws Exception {
        Match match = new Match("nameHomeTeam", "nameAwayTeam");

        List<Match> allMatches = Arrays.asList(match);

        given(matchService.getAllMatches()).willReturn(allMatches);

        mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].homeTeam").value(match.homeTeam))
                .andExpect(jsonPath("$[0].awayTeam").value(match.awayTeam));

    }

    @Test
    public void getMatch() throws Exception{
        Match match = new Match("nameHomeTeam", "nameAwayTeam");

        given(matchService.getMatch("nameHomeTeam", "nameAwayTeam")).willReturn(match);

        MvcResult result = mockMvc.perform(get("/compare-teams/nameHomeTeam/vs/nameAwayTeam")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.homeTeam").value(match.homeTeam))
                .andExpect(jsonPath("$.awayTeam").value(match.awayTeam))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

}