package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Portfolio {
    private ArrayList<Invest> investments;
    private HashMap<String, Invest> portfolio;


    //REQUIRES:
    //MODIFIES:
    //EFFECTS: constructs an empty hashmap with key being stock ticker and investments being invest objects associated
    //         with that exact ticker
    public Portfolio() {
        investments = new ArrayList<>();
        portfolio = new HashMap<String, Invest>();
    }


    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: if HashMap doesn't contain given string as a key, then create new hashmap key-val pair. Otherwise,
    //         add the new invest object to existing key-val pair
    public void addToPortfolio(String name, double listedPrice, double amountFunded) {
        if()


        }

    }






    public void sellInvestment(Invest invest) {
        investments.
    }

    public ArrayList<Invest> getInvestments() {
        return investments;
    }
}
