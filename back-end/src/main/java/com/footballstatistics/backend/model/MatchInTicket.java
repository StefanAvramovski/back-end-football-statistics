package com.footballstatistics.backend.model;

import javax.persistence.*;

@Entity
@Table
public class MatchInTicket {

    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "homeTeam")
    public String homeTeam;

    @Column(name = "awayTeam")
    public String awayTeam;

    @Column(name = "tip")
    public String tip;

    public MatchInTicket(){

    }

    public MatchInTicket(String homeTeam, String awayTeam, String tip){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.tip = tip;
    }

    public Long getId(){
        return this.id;
    }

    public void print(){
        System.out.println(this.id + " " + this.homeTeam + " " + this.awayTeam + " " + this.tip);
    }

}
