package model;


import org.json.JSONObject;
import persistence.Writable;

// Represents an investment having stock ticker name, listed price, amount funded, number of shares,
// profits, balance and realized gains (all in dollars)
public class Invest implements Writable {
    private String name;
    private double amountFunded;
    private double listedPrice;
    private double numberShares;
    private double profit;
    private double balance;
    private double realizedGains;


    //REQUIRES: name has a non-zero length
    //EFFECTS: constructs an investment with given name, given amount funded, given listed price/share
    //         the total number of shares purchased, the balance in the investment, zero profit and
    //         zero realized gains (money back from selling x-amount of shares)
    public Invest(String name, double listedPrice, double amountFunded) {
        this.name = name;
        this.amountFunded = amountFunded;
        this.listedPrice = listedPrice;
        this.numberShares = (amountFunded / listedPrice);
        this.balance = amountFunded;
        profit = 0.0;
        realizedGains = 0.0;
    }


   public Invest(String name, double listedPrice, double amountFunded, double numberShares, double balance,
                 double profit, double realizedGains) {
        this.name = name;
        this.listedPrice = listedPrice;
        this.amountFunded = amountFunded;
        this.numberShares = numberShares;
        this.balance = balance;
        this.profit = profit;
        this.realizedGains = realizedGains;
   }

    //REQUIRES: currentPrice > 0.0 && percentage <= 100.0  && percentage > 0.0
    //MODIFIES: this
    //EFFECTS: sell given percentage of shares at given current price/share. Deduct number of shares
    //         sold from total number of shares. Update the realized gains, balance and profit.
    public void sell(double currentPrice, double percentage) {
        realizedGains += ((numberShares * (percentage / 100)) * currentPrice);
        numberShares -= (numberShares * (percentage / 100));
        balance = (numberShares * listedPrice) + realizedGains;
        profit = balance - amountFunded;
    }


    public String getName() {
        return name;
    }

    public double getAmountFunded() {
        return amountFunded;
    }

    public double getListPrice() {
        return listedPrice;
    }

    public double getNumberShares() {
        return numberShares;
    }

    public double getProfit() {
        return profit;
    }

    public double getRealizedGains() {
        return realizedGains;
    }

    public double getBalance() {
        return balance;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("amountFunded", amountFunded);
        json.put("listedPrice", listedPrice);
        json.put("numberShares", getNumberShares());
        json.put("balance", getBalance());
        json.put("profit", getProfit());
        json.put("realizedGains", getRealizedGains());
        return json;
    }
}
