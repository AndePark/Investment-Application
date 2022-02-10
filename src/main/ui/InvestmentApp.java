package ui;

import model.Invest;
import model.Portfolio;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;


//Investment application
public class InvestmentApp {
    private Portfolio tickers;
    // private Portfolio sell;
    // private Portfolio buy;
    private ArrayList<Invest> investments;
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


    private void viewTickers() {
        Set<String> keys = tickers.keySet();
        for (String k : keys) {
            System.out.println(k);
        }
    }

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

    private void sellInvestment() {
        System.out.println("Enter Stock Ticker: ");
        String name = input.next();
        investments = tickers.getInvestments(name);

        for (Invest i : investments) {
            System.out.println(i.getNumberShares() + i.getListPrice());
            // i need to add a number here so that user can choose
        }
    }
}

    // i want to print out the listedprice (when we purchased it), the amount of shares im currently holding

/*

    private void sellInvest() {
        Invest chosen = chosenInvest();
        System.out.println("Enter Name of Investment You Wish to Sell: ");
        string name = input.next();
        System.out.println("Enter Today's Price/Share: $");
        double currentPrice = input.nextDouble();
        System.out.println("Enter Percentage of Shares to Sell: %");
        double percentage = input.nextDouble();

        if (currentPrice >= 0.0 && percentage <= 100 && percentage > 0) {
            chosen.sell(currentPrice, percentage);
        } else {
            System.out.println("Cannot Process Transaction");
        }
    }
    private void buyInvest() {
        Invest chosen = chosenInvest();
        System.out.println("Enter Name/Ticker");
        System.out.println("Enter Today's Price/Share: $");
        System.out.println("Enter Invested Amount: $");
        String name = input.next();
        double listedPrice = input.nextDouble();
        double amount = input.nextDouble();

        if (amount > 0 && listedPrice > 0) {
            chosen = new Invest(name, listedPrice, amount);
        } else {
            System.out.println("Cannot Process Transaction");
        }
    }

    private Portfolio selectProcess() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("b") || selection.equals("s") || selection.equals("v"))) {
            System.out.println("c for chequing");
            System.out.println("s for savings");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("c")) {
            return cheq;
        } else {
            return sav;
        }
    }


    }

}
*/