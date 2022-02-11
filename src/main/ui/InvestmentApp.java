package ui;

import model.Invest;
import model.Portfolio;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;


//Investment application
public class InvestmentApp {
    private Portfolio tickers;
    private Scanner input;

    public InvestmentApp() {
        runInvestment();
    }

    private void runInvestment() {
        boolean showProfile = true;
        String command = null;

        init();

        while (showProfile) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                showProfile = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nCome Back Soon!");
    }

    private void processCommand(String command) {
        if (command.equals("v")) {
            viewTickers();
        } else if (command.equals("b")) {
            buyInvestment();
        } else if (command.equals("s")) {
            sellInvestment();
        } else {
            System.out.println("Invalid Submission");
        }
    }

    private void init() {
        tickers = new Portfolio();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> View Tickers in Portfolio");
        System.out.println("\tb -> Buy");
        System.out.println("\ts -> Sell");
        System.out.println("\tq -> quit");
    }


    //EFFECTS: displays all purchased stock tickers in portfolio
    private void viewTickers() {
        Set<String> keys = tickers.keySet();
        for (String k : keys) {
            System.out.println(k);
        }
    }


    //MODIFIES: this
    //EFFECTS: conducts a purchase of investment
    private void buyInvestment() {
        System.out.println("Enter Stock Ticker: ");
        String name = input.next();
        System.out.println("Enter Listed Price/Share: $ ");
        double listedPrice = input.nextDouble();
        System.out.println("Enter Amount Funded: $ ");
        double amountFunded = input.nextDouble();

        if (listedPrice > 0.0 && amountFunded > 0.0) {
            tickers.addToPortfolio(name, listedPrice, amountFunded);
        } else {
            System.out.println("Invalid Entry");
        }
    }


    //MODIFIES:this, Invest
    //EFFECTS: conducts a sell of an investment
    private void sellInvestment() {
        System.out.println("Enter Stock Ticker: ");
        String name = input.next();
        ArrayList<Invest> investments;
        investments = tickers.getInvestments(name);

        for (int i = 0; i < investments.size(); i++) {
            System.out.println("Index:" + i);
            System.out.println("Number of Shares Holding:" + investments.get(i).getNumberShares());
            System.out.println("Purchased At:" +  investments.get(i).getListPrice() + "$/share" + "\n");
        }
        System.out.println("Enter Index Number:  ");
        int index = input.nextInt();
        System.out.println("Enter Current Price/Share: $ ");
        double currentPrice = input.nextDouble();
        System.out.println("Enter Percentage of Shares to Be Sold: % ");
        double percentage = input.nextDouble();

        if (index < investments.size() && currentPrice > 0 && percentage > 0 && percentage <= 100) {
            tickers.sellInvestInPortfolio(name, currentPrice, percentage, index);
            System.out.println("Realized Gains: " + investments.get(index).getRealizedGains());
            System.out.println("Number Of Shares Remaining: " + investments.get(index).getNumberShares());
            //System.out.println("Balance In Investment: " + investments.get(index).getBalance());
            System.out.println("Profit Gain/Loss: " + investments.get(index).getProfit());
        } else {
            System.out.println("Investment Cannot be Sold, 1 or More Fields Entered Incorrectly");
        }

    }
}
