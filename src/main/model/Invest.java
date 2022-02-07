package model;

public class Invest {


    private String name;
    private double amountFunded;
    //private String date;
    private double listedPrice;
    private double numberShares;
    private double profit;
    private double balance;
    private double soldReturn;


    //REQUIRES: name has a non-zero length
    //EFFECTS: constructs an investment with given name, zero amount funded and no profits
    public Invest(String name, double listedPrice, double amountFunded) {
        this.name = name;
        this.amountFunded = amountFunded;
        this.listedPrice = listedPrice;
        this.numberShares = (amountFunded / listedPrice);
        this.balance = amountFunded;
        profit = 0;
        soldReturn = 0;
    }

    //REQUIRES: percentage <= 100 && percentage > 0
    //EFFECTS: the given percentage of shares will be sold at the given current price, number of shares is now remainder
    // after selling and updated profit is returned
    public double sell(double currentPrice, int percentage) {
        this.soldReturn += (numberShares * (percentage / 100) * currentPrice);
        numberShares -= (numberShares * (percentage / 100));
        balance = (numberShares * listedPrice) + soldReturn;
        profit = balance - amountFunded;
        return profit;
    }

    public double totalBalance() {
        this.balance =
    }



    public double profit() {
        profit = balance - amountFunded;
        return profit;
    }


    //EFFECTS: all shares sold at given current price, number of shares is now 0 and updated profit is returned
    public double sellAll(double currentPrice) {
        profit = (numberShares * currentPrice) - balance;
        balance = 0;
        numberShares = 0;
        return profit;
    }

    //EFFECTS: half of total shares is sold at given current price, number of shares is now half the original
    // and updated profit is returned
    public double sellHalf(double currentPrice) {
        this.soldReturn = ((numberShares / 2) * currentPrice) + soldReturn;
        numberShares = numberShares / 2;
        balance = (numberShares * listedPrice) + soldReturn;
        profit = balance - amountFunded;
        return profit;
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

    public double getSoldReturn() {
        return soldReturn;
    }
    public double getBalance() {
        return balance;
    }

}
