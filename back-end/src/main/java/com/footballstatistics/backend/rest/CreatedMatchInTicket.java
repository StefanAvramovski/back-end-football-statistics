package com.footballstatistics.backend.rest;

public class CreatedMatchInTicket {
    public String homeTeam;
    public String awayTeam;
    public String tip;

    public CreatedMatchInTicket(){

    }

    public CreatedMatchInTicket(String homeTeam, String awayTeam, String tip){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.tip = tip;
    }
}
