package com.footballstatistics.backend.service.impl;

import com.footballstatistics.backend.model.MatchInTicket;
import com.footballstatistics.backend.model.exception.InvalidIdentifier;
import com.footballstatistics.backend.persistance.MatchInTicketRepository;
import com.footballstatistics.backend.rest.CreatedMatchInTicket;
import com.footballstatistics.backend.service.MatchInTicketService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MatchInTicketServiceImplTest {

    @Autowired
    private TestEntityManager entityManager;

    private MatchInTicketService matchInTicketService;

    @MockBean
    private MatchInTicketRepository matchInTicketRepository;

    @Before
    public void setUp(){
        matchInTicketService = new MatchInTicketServiceImpl(matchInTicketRepository);
    }

    @Test
    public void getAllMatches() {
        MatchInTicket matchInTicket = new MatchInTicket("nameHome", "nameAway", "1");
        List<MatchInTicket> listMatches = Arrays.asList(matchInTicket);
        Stream<MatchInTicket> streamMatches = listMatches.stream();

        Mockito.when(matchInTicketRepository.findAll()).thenReturn(streamMatches);

        assertEquals(matchInTicketService.getAllMatches(), listMatches);

    }

    @Test
    public void getMatchById() {
        Optional<MatchInTicket> optionalMatchInTicket = Optional.of(new MatchInTicket("nameHome", "nameAway", "1"));

        Mockito.when(matchInTicketRepository.findById(1L)).thenReturn(optionalMatchInTicket);

        assertEquals(matchInTicketService.getMatchById(1L), optionalMatchInTicket.get());

    }

    @Test
    public void create() {
        MatchInTicket matchInTicket = new MatchInTicket("nameHome", "nameAway", "1");

        Mockito.when(matchInTicketRepository.save(matchInTicket)).thenReturn(matchInTicket);

        CreatedMatchInTicket createdMatchInTicket = new CreatedMatchInTicket("nameHome", "nameAway", "1");

        matchInTicketService.create(createdMatchInTicket);

        verify(matchInTicketRepository, times(1)).save(isA(MatchInTicket.class));



    }

    @Test
    public void updateMatch() {
        MatchInTicket matchInTicket = new MatchInTicket("nameHome", "nameAway", "1");
        entityManager.persist(matchInTicket);
        entityManager.flush();

        Mockito.when(matchInTicketRepository.findById(matchInTicket.getId())).thenReturn(Optional.of(matchInTicket));
        Mockito.when(matchInTicketRepository.save(matchInTicket)).thenReturn(matchInTicket);

        MatchInTicket matchTest = matchInTicketService.updateMatch(matchInTicket.getId(), "2");

        verify(matchInTicketRepository, times(1)).save((isA(MatchInTicket.class)));
        assertEquals(matchInTicket.tip, matchTest.tip);

    }

    @Test
    public void updateMatchException() {
        Long id = null;
        MatchInTicket matchInTicket = new MatchInTicket();

        try {
            matchInTicketService.updateMatch(null, "");
        }catch (Exception ex){
            assertTrue(ex instanceof InvalidIdentifier);
        }

    }


    @Test
    public void removeMatch() {
        MatchInTicket matchInTicket = new MatchInTicket("nameHome", "nameAway", "1");
        entityManager.persist(matchInTicket);
        entityManager.flush();

        doNothing().when(matchInTicketRepository).deleteById(isA(Long.class));

        matchInTicketService.removeMatch(matchInTicket.getId());
    }
}