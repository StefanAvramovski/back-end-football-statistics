package com.footballstatistics.backend.persistance;

import com.footballstatistics.backend.model.MatchInTicket;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MatchInTicketRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MatchInTicketRepository matchInTicketRepository;

    private MatchInTicket matchInTicket;

    @Before
    public void setUp(){
        entityManager.clear();
        String homeTeam = "homeName";
        String awayTeam = "awayName";
        String tip = "1";
        matchInTicket = new MatchInTicket(homeTeam, awayTeam, tip);
    }

    @Test
    public void findByIdTest(){
        entityManager.persist(matchInTicket);
        entityManager.flush();

        Optional<MatchInTicket> matchInTicketFound = matchInTicketRepository.findById(matchInTicket.getId());

        assertEquals(matchInTicketFound.get().getId(), matchInTicket.getId());

    }

    @Test
    public void saveMatchInTicketTest(){
        matchInTicketRepository.save(matchInTicket);

        assertEquals(entityManager.getId(matchInTicket), matchInTicket.getId());
    }

    @Test
    public void deleteByIdTest(){
        entityManager.persist(matchInTicket);
        entityManager.flush();

        matchInTicketRepository.deleteById(matchInTicket.getId());

        assertEquals(matchInTicketRepository.findById(matchInTicket.getId()), Optional.empty());

    }

    @After
    public void after(){
        entityManager.clear();
    }

}