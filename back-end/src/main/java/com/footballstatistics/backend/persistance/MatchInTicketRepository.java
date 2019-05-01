package com.footballstatistics.backend.persistance;


import com.footballstatistics.backend.model.MatchInTicket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public interface MatchInTicketRepository extends Repository<MatchInTicket, Long> {

    Stream<MatchInTicket> findAll();

    Optional<MatchInTicket> findById(Long id);

    MatchInTicket save(MatchInTicket match);

    @Modifying
    void deleteById(Long id);

}
