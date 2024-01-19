package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    static ArrayList<Playerinformation> playerList = new ArrayList<>();

    public static void loadDataFromCSV(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();


            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");


                String name = values[0];
                String team = values[1];
                String role = values[2];
                int matches = Integer.parseInt(values[3]);
                int runs = Integer.parseInt(values[4]);

                double average = 0;
                    try {
                        average = Double.parseDouble(values[5]);
                    } catch (NumberFormatException e) {
                        log.error("Error parsing as double at line: ");
                        log.error(line);
                    }

                double strikerate = 0;
                try {
                    strikerate = Double.parseDouble(values[6]);
                } catch (NumberFormatException e) {
                    log.error("Error parsing as double at line: ");
                    log.error(line);
                }


                int wickets = Integer.parseInt(values[7]);
                    Playerinformation player = new Playerinformation(name, team, role, matches, runs, average, strikerate, wickets);
                    playerList.add(player);
            }
        } catch (IOException e) {
            log.error("Failed reading the file.");
        }

    }

    public static  void showPlayerDetails(Playerinformation player){
            log.info(String.valueOf(player));
    }

    public static void bowlersWithAtleast40W(String teamName){
        for (Playerinformation player: playerList) {
            if(player.getTeam().equals(teamName) && player.getWickets()>=40){
               showPlayerDetails(player);
            }
        }
    }

    public static void bestPlayers(String teamName){
        int score = 0;
        Playerinformation highestScorer = null;
        for (Playerinformation player: playerList) {
            if(player.getTeam().equals(teamName) && player.getRuns() > score){
                highestScorer = player;
                score = player.getRuns();
            }
        }
        log.info("Highest Run Scorer for team {} is: ", teamName);
        showPlayerDetails(highestScorer);

        Playerinformation highestWicketTaker = null;
        int wickets = 0;
        for (Playerinformation player: playerList) {
            if(player.getTeam().equals(teamName) && player.getWickets() > wickets){
                highestWicketTaker = player;
                wickets = player.getWickets();
            }
        }
        log.info("Highest Wicket Taker for team {} is: ", teamName);
        showPlayerDetails(highestWicketTaker);
    }

    public static void topThreeRunScorers(){
        playerList.sort((o1, o2) -> {
            if (o1.getRuns() == o2.getRuns())
                return 0;
            return o1.getRuns() > o2.getRuns() ? -1 : 1;
        });

        Iterator<Playerinformation> playerIterator = playerList.iterator();
        int i = 0;
        while(playerIterator.hasNext() && i<3){
            Playerinformation player = playerIterator.next();
            showPlayerDetails(player);
                i++;
        }
    }

    public static void topThreeWicketTakers(){
        playerList.sort((o1, o2) -> {
            if (o1.getWickets() == o2.getWickets())
                return 0;
            return o1.getWickets() > o2.getWickets() ? -1 : 1;
        });


        Iterator<Playerinformation> playerIterator = playerList.iterator();
        int i = 0;
        while(playerIterator.hasNext() && i<3){
            Playerinformation player = playerIterator.next();
            showPlayerDetails(player);
            i++;
        }

    }


    public static void main(String[] args) {
        String filePath = "src/main/resources/IPL_2021-data.csv";
        loadDataFromCSV(filePath);

        log.info("Enter 1 to get bowlers with atleast 40 wickets from a specific team\nEnter 2 to get highest run scorer and highest wickettaker from a team\nEnter 3 to get top 3 run scorers of the season\nEnter 4 to get top 3 wicket takers of the season");

        Scanner scn = new Scanner(System.in);
        int choice = scn.nextInt();
        String teamName = "RCB";
        switch(choice) {
            case 1:
            bowlersWithAtleast40W(teamName);
            break;
            case 2:
            bestPlayers(teamName);
            break;
            case 3:
            topThreeRunScorers();
            break;
            case 4:
            topThreeWicketTakers();
            break;
            default:
                break;
        }

        //4th part
        String[] teamNamesList = new String[10];
        log.info("Enter team names: ");
        for(int i = 0; i<10; i++){
            teamNamesList[i] = scn.next();
        }

        //making match combinations
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
log.info(teamNamesList[i] + " matches with " + teamNamesList[j]);
            }
        }

    }
}

