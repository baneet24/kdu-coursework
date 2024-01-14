package org.example;
import java.util.HashMap;
import java.util.Map;

public class Coin {
    private final int rank;
    private final String name;
    private final String symbol;
    private double price;
    private long circulatingSupply;

    public static Map<String, Long> coinTraders = new HashMap<>();

    private static final LoggerService log = new LoggerService();

    public Coin(int rank, String name, String symbol, double price, long circulatingSupply) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.circulatingSupply = circulatingSupply;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "rank=" + rank +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                ", circulatingSupply=" + circulatingSupply +
                '}';
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setSupply(long supply){
        this.circulatingSupply = supply;
    }

    public String getSymbol(){
        return symbol;
    }
    public long getSupply(){
        return circulatingSupply;
    }

    public long getSupplyFromWalletAddress(String walletAddress){
        return coinTraders.get(walletAddress);
    }

    public void setSupplyByWalletAddress(String walletAddress, long supply){
        if (coinTraders.containsKey(walletAddress)) {
            coinTraders.replace(walletAddress, supply);
            log.logInfo("Map after update: " + coinTraders);
        } else {
            coinTraders.put(walletAddress,supply);
            log.logError("Key not found");

        }
    }

    public Map<String, Long> getCoinTraders() {
        return coinTraders;
    }

}
