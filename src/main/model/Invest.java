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
        soldReturn = 0.0;
    }


    public void sell(double currentPrice, double percentage) {
        soldReturn += ((numberShares * (percentage / 100)) * currentPrice);
        numberShares -= (numberShares * (percentage / 100));
        balance = (numberShares * listedPrice) + soldReturn;
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

    public double getSoldReturn() {
        return soldReturn;
    }
    public double getBalance() {
        return balance;
    }

}
