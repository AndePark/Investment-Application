package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

// referenced JsonTutorial.WorkRoom for toJson()/ took inspiration for thingsToPortfolioToJson() && investmentsToJson()
// model: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


// Represents an investment portfolio having stock ticker name associated to list of investments for that specific
// stock
public class Portfolio implements Writable {
    private HashMap<String, ArrayList<Invest>> portfolio;

    //EFFECTS: constructs an investment portfolio with stock ticker names each associated to list of investments made
    //         for that specific stock ticker
    public Portfolio() {
        portfolio = new HashMap<>();

    }

    //REQUIRES: listedPrice > 0.0 && amountFunded > 0.0
    //MODIFIES: this
    //EFFECTS: if given stock ticker name is in portfolio, add a new Invest with given name, listed price/share
    //         and amount funded to the Investments list associated to that name. Otherwise, a new portfolio
    //         name is created with given stock ticker name, and a new Invest with given string name,
    //         listed price/share and amountFunded is added to the new Investment list associated to the new
    //         portfolio name.
    public void addToPortfolio(String name, double listedPrice, double amountFunded) {
        if (portfolio.containsKey(name)) {
            portfolio.get(name).add(new Invest(name, listedPrice, amountFunded));
        } else {
            ArrayList<Invest> investments = new ArrayList<>();
            investments.add(new Invest(name, listedPrice, amountFunded));
            portfolio.put(name, investments);
        }
    }

    //REQUIRES: listedPrice, amountFunded,numShares, balance, profit && realizedGains > 0.0
    //MODIFIES: this
    //EFFECTS: if given stock ticker name is in portfolio, add a new Invest with given name, listed price/share
    //         amount funded, numShares, balance, profit and realizedGains
    //         to the Investments list associated to that name. Otherwise, a new portfolio
    //         name is created with given stock ticker name, and a new Invest with given string name,
    //         listed price/share, amountFunded, numShares, balance, profit, and realizedGains
    //         is added to the new Investment list associated to the new portfolio name.
    public void addToPortfolio(String name, double listedPrice, double amountFunded,
                               double numShares, double balance, double profit, double realizedGains) {
        if (portfolio.containsKey(name)) {
            portfolio.get(name).add(new Invest(name, listedPrice,
                    amountFunded, numShares, balance, profit, realizedGains));
        } else {
            ArrayList<Invest> investments = new ArrayList<>();
            investments.add(new Invest(name, listedPrice, amountFunded, numShares, balance, profit, realizedGains));
            portfolio.put(name, investments);
        }
    }


    public void addToPortfolio(Invest investment) {
        if (portfolio.containsKey(investment.getName())) {
            portfolio.get(investment.getName()).add(investment);
        } else {
            ArrayList<Invest> investments = new ArrayList<>();
            investments.add(investment);
            portfolio.put(investment.getName(), investments);
        }
    }

    //REQUIRES: name must be in Portfolio && index must be associated to an investment in the list
    //MODIFIES: this, Invest
    //EFFECTS: chosen investment (via index) in the list of investments associated with given stock ticker name is sold
    //         at given current price/share and given percentage of shares the user wants to sell.
    public void sellInvestInPortfolio(String name, double currentPrice, double percentage, int index) {
        portfolio.get(name).get(index).sell(currentPrice, percentage);
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

    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Portfolio", thingsToPortfolioToJson());
        return jsonObj;
    }

    //EFFECTS: returns ticker name and its associated investments
    // in this portfolio as a JSON array
    private JSONArray thingsToPortfolioToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String key : portfolio.keySet()) {
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("name", key);
            jsonobject.put("investments", investmentsToJson(portfolio.get(key)));
            jsonArray.put(jsonobject);

        }
        return jsonArray;
    }

    //EFFECTS: returns investments in this portfolio as a JSON array
    private JSONArray investmentsToJson(ArrayList<Invest> investments) {
        JSONArray jsonArray = new JSONArray();

        for (Invest i : investments) {
            jsonArray.put(i.toJson());
        }
        return jsonArray;
    }
}