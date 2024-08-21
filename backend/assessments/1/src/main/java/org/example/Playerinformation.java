package org.example;

public class Playerinformation {
    String  name;
    String team;
    String role;
    int matches;
    int runs;
    Double average;
    Double strikerate;
    int wickets;

    public Playerinformation(String  name, String team, String role, int matches, int runs, Double average, Double strikerate, int wickets){
        this.name = name;
        this.team = team;
        this.role = role;
        this.matches = matches;
        this.runs = runs;
        this.average = average;
        this.strikerate = strikerate;
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", team='" + team + '\'' +
                ", role='" + role + '\'' +
                ", matches='" + matches + '\''+
                ", runs='" + runs + '\''+
                ", average='" + average + '\'' +
                ", strikerate='" + strikerate + '\'' +
                ", wickets='" + wickets + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public String getRole() {
        return role;
    }

    public int getMatches() {
        return matches;
    }

    public int getRuns() {
        return runs;
    }

    public Double getAverage() {
        return average;
    }

    public Double getStrikerate() {
        return strikerate;
    }

    public int getWickets() {
        return wickets;
    }
}
