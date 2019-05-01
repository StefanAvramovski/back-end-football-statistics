package com.footballstatistics.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "date")
    public String date;

}
