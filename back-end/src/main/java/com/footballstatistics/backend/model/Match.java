package com.footballstatistics.backend.model;

import java.util.ArrayList;

public class Match {

    public String homeTeam;

    public String homeTeamCountry;

    public ArrayList<Integer> homeTeamLastNineGoals;

    public ArrayList<Integer> homeTeamHomeLastNineGoals;

    public String awayTeam;

    public String awayTeamCountry;

    public ArrayList<Integer> awayTeamLastNineGoals;

    public ArrayList<Integer> awayTeamAwayLastNineGoals;

    public ArrayList<Integer> againstEachOther;

    public ArrayList<Integer> againstEachOtherHomeAway;

    public String time;

    public String averageTotalGoals;

    public String averageTotalGoalsChanged;

    public Match(){

    }

    public Match(String homeTeam,String awayTeam){
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }


    public Match(String time, String homeTeam, String homeTeamCountry, String awayTeam, String awayTeamCountry, String averageTotalGoals, String averageTotalGoalsChanged) {
        this.time = time;
        this.homeTeamCountry = homeTeamCountry;
        this.awayTeamCountry = awayTeamCountry;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamLastNineGoals = new ArrayList<>();
        this.homeTeamHomeLastNineGoals = new ArrayList<>();
        this.awayTeamLastNineGoals = new ArrayList<>();
        this.awayTeamAwayLastNineGoals = new ArrayList<>();
        this.againstEachOther = new ArrayList<>();
        this.againstEachOtherHomeAway = new ArrayList<>();
        this.averageTotalGoals = averageTotalGoals;
        this.averageTotalGoalsChanged = averageTotalGoalsChanged;
    }

    public void setHomeTeamLastNineGoals(ArrayList<Integer> list){
        this.homeTeamLastNineGoals = list;
    }

    public void setHomeTeamHomeLastNineGoals(ArrayList<Integer> list){
        this.homeTeamHomeLastNineGoals = list;
    }

    public void setAwayTeamLastNineGoals(ArrayList<Integer> list){
        this.awayTeamLastNineGoals = list;
    }

    public void setAwayTeamAwayLastNineGoals(ArrayList<Integer> list){
        this.awayTeamAwayLastNineGoals = list;
    }

    public void setAgainstEachOther(ArrayList<Integer> list){
        this.againstEachOther = list;
    }

    public void setAgainstEachOtherHomeAway(ArrayList<Integer> list){
        this.againstEachOtherHomeAway = list;
    }

}
