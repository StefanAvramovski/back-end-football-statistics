package com.footballstatistics.backend.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.footballstatistics.backend.model.MatchInTicket;
import com.footballstatistics.backend.service.impl.MatchInTicketServiceImpl;
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

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
public class MatchInTicketControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private MatchInTicketServiceImpl matchInTicketService;

    @Before
    public void setUp(){
        MatchInTicketController matchInTicketController = new MatchInTicketController(matchInTicketService);

        mockMvc = standaloneSetup(matchInTicketController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void listMatches() throws Exception{
        MatchInTicket matchInTicket = new MatchInTicket("home", "away", "1");
        List<MatchInTicket> allMatchesInTicket = Arrays.asList(matchInTicket);

        given(matchInTicketService.getAllMatches()).willReturn(allMatchesInTicket);

        MvcResult result = mockMvc.perform(get("/my-matches")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].homeTeam").value(matchInTicket.homeTeam))
                .andExpect(jsonPath("$[0].awayTeam").value(matchInTicket.awayTeam))
                .andExpect(jsonPath("$[0].tip").value(matchInTicket.tip))
                .andReturn();

        String res = result.getResponse().getContentAsString();

    }

    @Test
    public void addMatch() throws Exception{
        CreatedMatchInTicket createdMatchInTicket = new CreatedMatchInTicket("home", "away", "1");

        given(matchInTicketService.create(createdMatchInTicket)).willReturn(new MatchInTicket("home", "away","1"));

        MvcResult result = mockMvc.perform(post("/my-matches")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(
                        new CreatedMatchInTicket("home", "away", "1"))))
                .andExpect(status().isOk())
                .andReturn();

        String res = result.getResponse().getContentAsString();

        System.out.println(res);
    }

    @Test
    public void updateMatch() throws Exception{
        Long id = 1L;

        MatchInTicket matchInTicket = new MatchInTicket("home", "away", "1");

        given(matchInTicketService.updateMatch(id, "1")).willReturn(matchInTicket);

        MvcResult result = mockMvc.perform(patch("/my-matches/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(
                        new CreatedMatchInTicket("home", "away", "1"))))
                .andExpect(status().isOk())
                .andReturn();

        String res = result.getResponse().getContentAsString();
        String expected = "{\"id\":null,\"homeTeam\":\"home\",\"awayTeam\":\"away\",\"tip\":\"1\"}";

        assertEquals(res, expected);

    }

    @Test
    public void removeMatch() throws Exception{

        doNothing().when(matchInTicketService).removeMatch(isA(Long.class));

        mockMvc.perform(delete("/my-matches/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void removeMatchException() throws Exception{

        doThrow(new NullPointerException()).when(matchInTicketService).removeMatch(null);

        try {
            mockMvc.perform(delete("/my-matches/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }catch(Exception ex) {
            assertTrue(ex instanceof NullPointerException);
        }
    }


}