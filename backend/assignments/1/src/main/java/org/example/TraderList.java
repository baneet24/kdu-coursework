package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;


public class TraderList {
    LoggerService log = new LoggerService();
     static List<Trader> traderArr = new CopyOnWriteArrayList<>();

    public void makeTradersList(String tradersCsvFile){

        try (BufferedReader br = new BufferedReader(new FileReader(tradersCsvFile))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String firstName = values[1];
                String lastName = values[2];
                String phone = values[3];
                String walletAddress = values[4];

                Trader trader = new Trader(firstName, lastName, phone, walletAddress);
                traderArr.add(trader);
            }
        } catch (IOException e) {
            log.logError("Error reading the file: " + e.getMessage());
        }

    }
    public void showTradersDetails(){
        for (Trader trader : traderArr) {
          log.logInfo(String.valueOf(trader));
     }
    }

    public double retrieveProfitFromWalletAddress(String walletAddress){
        Trader t = retrieveTraderDetailsFromWalletAddress(walletAddress);
        return t.getProfit();
    }
    public Trader retrieveTraderDetails(String firstName, String lastName) {
        return traderArr.stream()
                .filter(trader -> Objects.equals(trader.getFirstName(), firstName) && Objects.equals(trader.getLastName(), lastName))
                .findFirst()
                .orElse(null);
    }

    public Trader retrieveTraderDetailsFromWalletAddress(String walletAddress) {

        return traderArr.stream()
                .filter(trader -> trader.getWalletAddress().equals(walletAddress) )
                .findFirst()
                .orElse(null);
    }

    public void topAndBottomFiveTraders(){
        topFiveTraders();
        bottomFiveTraders();
    }

    public void bottomFiveTraders(){

        traderArr.stream()
                .sorted((t1,t2)-> Double.compare(t1.getProfit(), t2.getProfit()))
                .limit(5)
                .forEach(t -> log.logInfo("\nFirst Name : " + t.getFirstName() + "\nLastName : " + t.getLastName() + "\nPhone number : " + t.getPhoneNumber() + "\nWallet Address : " +  t.getWalletAddress()));
    }
    public void topFiveTraders(){

        traderArr.stream()
                .sorted((t1,t2)-> Double.compare(t2.getProfit(), t1.getProfit()))
                .limit(5)
                .forEach(t -> log.logInfo("\nFirst Name : " + t.getFirstName() + "\nLastName : " + t.getLastName() + "\nPhone number : " + t.getPhoneNumber() + "\nWallet Address : " +  t.getWalletAddress()));
    }
}
