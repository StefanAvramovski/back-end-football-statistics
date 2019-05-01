package com.footballstatistics.backend.persistance;

import com.footballstatistics.backend.model.Ticket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface TicketRepository extends Repository<Ticket, Long> {

}
