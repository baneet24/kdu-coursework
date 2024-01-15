package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.StreamSupport;

public class TransactionReader {

    private TransactionReader(){}
    public static List<Transaction> readTransactionsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(reader);

            return StreamSupport.stream(root.spliterator(), false)
                    .map(TransactionReader::createTransactionFromNode) .toList();
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private static Transaction createTransactionFromNode(JsonNode transactionNode) {
        String type = transactionNode.get("type").asText();
        Transaction result;
        switch (type) {
            case "BUY":
                result = createBuyTransaction(transactionNode.get("data"));
                break;
            case "UPDATE_PRICE":
                result = createUpdatePriceTransaction(transactionNode.get("data"));
                break;
            case "ADD_VOLUME":
                result = createAddVolumeTransaction(transactionNode.get("data"));
                break;
            case "SELL":
                result = createSellTransaction(transactionNode.get("data"));
                break;
            default:
                result = null;
                break;
        }
        return result;
    };


    private static BuyTransaction createBuyTransaction(JsonNode dataNode) {
        String coin = dataNode.get("coin").asText();
        int quantity = dataNode.get("quantity").asInt();
        String walletAddress = dataNode.get("wallet_address").asText();

        return new BuyTransaction(coin, quantity, walletAddress);
    }

    private static UpdatePriceTransaction createUpdatePriceTransaction(JsonNode dataNode) {
        String coin = dataNode.get("coin").asText();
        double price = dataNode.get("price").asDouble();

        return new UpdatePriceTransaction(coin, price);
    }

    private static AddVolumeTransaction createAddVolumeTransaction(JsonNode dataNode) {
        String coin = dataNode.get("coin").asText();
        long volume = dataNode.get("volume").asLong();

        return new AddVolumeTransaction(coin, volume);
    }

    private static SellTransaction createSellTransaction(JsonNode dataNode) {
        String coin = dataNode.get("coin").asText();
        int quantity = dataNode.get("quantity").asInt();
        String walletAddress = dataNode.get("wallet_address").asText();

        return new SellTransaction(coin, quantity, walletAddress);
    }

}