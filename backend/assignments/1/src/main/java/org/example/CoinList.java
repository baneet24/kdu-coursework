package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;


public class CoinList {

    private static final LoggerService log = new LoggerService();
    public  List<Coin> coinListArr = new CopyOnWriteArrayList<>();

    private TraderList traderList;
    public CoinList(TraderList traderList) {
        this.traderList = traderList;
    }

    public  synchronized void updatePrice(Coin coin, double price){

        coin.getCoinTraders().forEach((walletAddress, quantity) -> {

            Trader trader = traderList.retrieveTraderDetailsFromWalletAddress(walletAddress);
            if (trader != null) {
                double profit = calculateProfit(coin.getPrice(), price, quantity);
                trader.setProfit(profit);
            }
        });
        coin.setPrice(price);
    }

    public Coin retrieveCoinDetailsBySymbol(String s) {
        return coinListArr.stream()
                .filter(coin -> coin.getSymbol().equals(s))
                .findFirst()
                .orElse(null);

    }

    public  synchronized void buyCoin(String coin, int quantity, String walletAddress){
        Coin c = retrieveCoinDetailsBySymbol(coin);
        if(c.getSupply()>=quantity){
            c.setSupply(c.getSupply()-quantity);
            c.setSupplyByWalletAddress(walletAddress,quantity);
        }
        else{
            log.logInfo("Can not buy coins due to insufficient supply");
        }
    }

    public synchronized void sellCoin(String coin, int quantity, String walletAddress){

        Coin c = retrieveCoinDetailsBySymbol(coin);
        if(c!=null){
            if(quantity <= c.getSupplyFromWalletAddress(walletAddress)){
                c.setSupply(c.getSupply() + quantity);
                c.setSupplyByWalletAddress(walletAddress, c.getSupplyFromWalletAddress(walletAddress) - quantity);
                log.logInfo("Successfully sold the coins");
            }
            else{
                log.logError("Not enough coins to sell");
            }
        }
        else{
            log.logError("No coins to sell as the coins are not bought");
        }
    }

    public synchronized void addVolume(String coin, long volume){
        Coin c = retrieveCoinDetailsBySymbol(coin);
        c.setSupply(c.getSupply() + volume);
    }

    public void makeCoinList(String coinsCsvFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(coinsCsvFile))) {
            String line = br.readLine();


            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2) {

                    int rank = Integer.parseInt(values[1]);
                    String name = values[2];
                    String symbol = values[3];
                    double price = 0;
                    try {
                        price = Double.parseDouble(values[4]);
                    } catch (NumberFormatException e) {
                        log.logError("Error parsing as double at line: " + line);
                    }
                    long circulatingSupply = Long.parseLong(values[5]);
                    Coin coin = new Coin(rank, name, symbol, price, circulatingSupply);
                    coinListArr.add(coin);
                } else {
                    log.logError("Invalid line in CSV: " + line);
                }
            }
        } catch (IOException e) {
            log.logError("Failed reading the file.");
        }

        for (Coin coin : coinListArr) {
            log.logInfo(String.valueOf(coin));
        }
    }
    public void showCoinDetails(){
        for (Coin coin: coinListArr) {
            log.logInfo(String.valueOf(coin));
        }
    }
    public Coin retrieveCoinDetails(String coinName) {
        return coinListArr.stream()
                .filter(coin -> Objects.equals(coin.getName(), coinName))
                .findFirst()
                .orElse(null);
    }

    public void getTopNCoins(int n) {
        coinListArr.stream()
                .sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .limit(n)
                .forEach(coin -> log.logInfo("\nName : " + coin.getName() + "\nSymbol : " + coin.getSymbol() + "\nPrice : " + coin.getPrice() + "\nSupply : " +  coin.getSupply()));
    }

    public double calculateProfit(double newPrice, double oldPrice, long quantity){
        return (newPrice - oldPrice)*quantity;
    }
}
