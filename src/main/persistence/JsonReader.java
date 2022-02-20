package persistence;


import model.Invest;
import model.Portfolio;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


// Represent a reader that reads portfolio from JSON data stored in file
public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads portfolio from file and returns it
    // throws IOException if an error occurs reading data from file
    public Portfolio read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parsedPortfolio(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


    // EFFECTS: parses portfolio from JSON array and returns it
    private Portfolio parsedPortfolio(JSONArray jsonArray) {
        Portfolio pr = new Portfolio();
        for (Object json : jsonArray) {
            JSONObject stockInvestmentCombo = (JSONObject) json;
            addItems(pr, stockInvestmentCombo);
        }
        return pr;
    }


    // parses
    private void addItems(Portfolio pr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray jsonArray = jsonObject.getJSONArray("investments");
        for (Object json1 : jsonArray) {
            JSONObject nextInvestments = (JSONObject) json1;
            addItem(pr, nextInvestments);
        }

    }

    private void addItem(Portfolio pr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double listedPrice = jsonObject.getDouble("listedPrice");
        Double amountFunded = jsonObject.getDouble("amountFunded");
        Double numShares = jsonObject.getDouble("numberShares");
        Double balance = jsonObject.getDouble("balance");
        Double profit = jsonObject.getDouble("profit");
        Double realizedGains = jsonObject.getDouble("realizedGains");

        pr.addToPortfolio(name, listedPrice, amountFunded, numShares, balance, profit, realizedGains);


    }
}




