package ui;

import model.Invest;
import model.Portfolio;
import persistence.JsonReader;
import persistence.JsonWriter;


// referenced Teller UI for displayMenu && command
// model: ca.ubc.cpsc210.bank.ui.TellerApp from https://github.students.cs.ubc.ca/CPSC210/TellerApp

// referenced JsonTutorial UI for savePortfolio() && loadPortfolio()
// model: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;



//Investment application
public class InvestmentApp {
    private static final String JSON_STORE = "./data/portfolio.json";
    private Portfolio portfolio;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: runs the investment application
    public InvestmentApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        portfolio = new Portfolio();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runInvestment();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runInvestment() {
        boolean showProfile = true;
        String command = null;
        input = new Scanner(System.in);

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

    // MODIFIES: this
    // EFFECTS: processes user command
    @SuppressWarnings ("methodlength")
    private void processCommand(String command) {
        switch (command) {
            case "v":
                viewTickers();
                break;
            case "t":
                viewByTicker();
                break;
            case "p":
                viewTotalProfit();
                break;
            case "b":
                buyInvestment();
                break;
            case "s":
                sellInvestment();
                break;
            case "c":
                savePortfolio();
                break;
            case "l":
                loadPortfolio();
                break;
            default:
                System.out.println("Invalid Submission");
                break;
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> View Tickers");
        System.out.println("\tt -> View Information for Individual Ticker");
        System.out.println("\tp -> View Total Profits for Ticker");
        System.out.println("\tb -> Buy Investment");
        System.out.println("\ts -> Sell Investment");
        System.out.println("\tc -> Save Portfolio to File");
        System.out.println("\tl -> Load Portfolio from File");
        System.out.println("\tq -> Quit");
    }

    //EFFECTS: display information of all investments for given stock ticker name currently in portfolio
    private void viewByTicker() {
        System.out.println("Enter Stock Ticker: ");
        String name = input.next();
        if (portfolio.containsKey(name)) {
            ArrayList<Invest> investments;
            investments = portfolio.getInvestments(name);

            for (Invest investment : investments) {
                System.out.println("Amount Funded: $" + investment.getAmountFunded());
                System.out.println("Purchased At: " + investment.getListPrice() + "$/share");
                System.out.println("Number Of Shares Holding:" + investment.getNumberShares());
                System.out.println("Profit Gain/Loss: $" + investment.getProfit() + "\n");
            }
        } else {
            System.out.println("Given Stock Ticker Does Not Exist in Portfolio");
        }
    }

    //EFFECTS: displays total profits for given stock ticker
    private void viewTotalProfit() {
        System.out.println("Enter Stock Ticker: ");
        String name = input.next();

        if (portfolio.containsKey(name)) {
            ArrayList<Invest> investments;
            investments = portfolio.getInvestments(name);

            int profit = 0;

            for (Invest investment : investments) {
                profit += investment.getProfit();
            }
            System.out.println("Total Profits/Loss for " + name + " is: $" + profit);
        } else {
            System.out.println("Given Stock Ticker Does Not Exist in Portfolio");
        }
    }

    //EFFECTS: displays all purchased stock tickers in portfolio
    private void viewTickers() {
        Set<String> keys = portfolio.keySet();
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
            portfolio.addToPortfolio(name, listedPrice, amountFunded);
        } else {
            System.out.println("Invalid Entry");
        }
    }


    //MODIFIES: this, Invest
    //EFFECTS: conducts a sell of an investment
    private void sellInvestment() {
        System.out.println("Enter Stock Ticker: ");
        String name = input.next();
        ArrayList<Invest> investments;
        investments = portfolio.getInvestments(name);

        for (int i = 0; i < investments.size(); i++) {
            System.out.println("Index:" + i);
            System.out.println("Number of Shares Holding:" + investments.get(i).getNumberShares());
            System.out.println("Purchased At:" + "$" + investments.get(i).getListPrice() + "/share" + "\n");
        }
        System.out.println("Enter Index Number:  ");
        int index = input.nextInt();
        System.out.println("Enter Current Price/Share: $ ");
        double currentPrice = input.nextDouble();
        System.out.println("Enter Percentage of Shares to Be Sold: % ");
        double percentage = input.nextDouble();

        if (index < investments.size() && currentPrice > 0 && percentage > 0 && percentage <= 100) {
            portfolio.sellInvestInPortfolio(name, currentPrice, percentage, index);
            System.out.println("Realized Gains: $" + investments.get(index).getRealizedGains());
            System.out.println("Number Of Shares Remaining: " + investments.get(index).getNumberShares());
            System.out.println("Profit Gain/Loss: $" + investments.get(index).getProfit());
        } else {
            System.out.println("Investment Cannot be Sold, 1 or More Fields Entered Incorrectly");
        }

    }

    //EFFECTS: saves the portfolio to file
    private void savePortfolio() {
        try {
            jsonWriter.open();
            jsonWriter.write(portfolio);
            jsonWriter.close();
            System.out.println("Saved Portfolio to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to Write to File: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads workroom from file
    private void loadPortfolio() {
        try {
            portfolio = jsonReader.read();
            System.out.println("Loaded Portfolio from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to Read from File " + JSON_STORE);
        }
    }
}
