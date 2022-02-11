package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Portfolio {
    private ArrayList<Invest> investments;
    private HashMap<String, ArrayList<Invest>> portfolio;

    //EFFECTS: constructs an investment portfolio with stock ticker names each associated to list of investments made
    //         for that specific stock ticker
    public Portfolio() {
        portfolio = new HashMap<>();

    }

    //REQUIRES: listedPrice > 0.0 && amountFunded > 0.0
    //MODIFIES: this
    //EFFECTS: if given stock ticker name is in portfolio, add a new Invest with given name, listedPrice
    //         and amountFunded to the Investments list associated to that name. Otherwise, a new portfolio
    //         name is created with given stock ticker name, and a new Invest with given string name, listedPrice and
    //         amountFunded is added to the new Investment list associated to the new portfolio name.
    public void addToPortfolio(String name, double listedPrice, double amountFunded) {
        if (portfolio.containsKey(name)) {
            investments = portfolio.get(name);
            investments.add(new Invest(name, listedPrice, amountFunded));
        } else {
            investments = new ArrayList<Invest>();
            investments.add(new Invest(name, listedPrice, amountFunded));
            portfolio.put(name, investments);
        }
    }

    //REQUIRES: name must be in Portfolio && index must be associated to an investment in the list
    //MODIFIES: this, Invest
    //EFFECTS: chosen investment (via index) in the list of investments associated with given stock ticker name is sold
    //         at given currentPrice and given percentage of shares the user wants to sell.
    public void sellInvestInPortfolio(String name, double currentPrice, double percentage, int index) {
        investments = portfolio.get(name);
        investments.get(index).sell(currentPrice, percentage);
    }

    public ArrayList<Invest> getInvestments(String name) {
        return portfolio.get(name);
    }

    public Set<String> keySet() {
        return portfolio.keySet();
    }

    //returns size of hashmap
    public int getSize() {
        return portfolio.size();
    }

    public boolean isEmpty() {
        return portfolio.isEmpty();
    }

    public boolean containsKey(String name) {
        return portfolio.containsKey(name);
    }
}