package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

// Represents an investment portfolio having stock ticker name associated to list of investments for that specific
// stock
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
            investments = new ArrayList<>();
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

    //REQUIRES: stock ticker name must be in portfolio
    //EFFECTS: returns the list of investments for the given stock ticker name
    public ArrayList<Invest> getInvestments(String name) {
        return portfolio.get(name);
    }


    //EFFECTS: returns a set of all stock ticker names in portfolio
    public Set<String> keySet() {
        return portfolio.keySet();
    }

    //EFFECTS: returns the size of portfolio (based off number of stock tickers in portfolio)
    public int getSize() {
        return portfolio.size();
    }

    //EFFECTS: returns true if portfolio is empty. Otherwise, false.
    public boolean isEmpty() {
        return portfolio.isEmpty();
    }


    //EFFECTS: returns true if stock ticker name is associated with list of investments. Otherwise, false.
    public boolean containsKey(String name) {
        return portfolio.containsKey(name);
    }
}