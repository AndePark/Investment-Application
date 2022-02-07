package ui;

import model.Invest;


import java.util.Scanner;


//Investment application
public class InvestmentApp {
    private Invest sell;
    private Invest buy;
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
            viewInvestments();
        } else if (command.equals("t")) {
            makeTransaction();
        } else {
            System.out.println("Invalid Submission");
        }
    }

    private void init() {

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> View Portfolio");
        System.out.println("\tt -> Make A Transaction (Sell/Buy)");
        System.out.println("\tq -> quit");
    }



    private void makeTransaction() {
        Invest chosen = chosenInvest();
        if (chosen.equals(sell)) {
            sellInvest();
        } else {
            buyInvest();
        }
    }

    private void viewInvestments() {
        //stub
    }

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

    private Invest chosenInvest() {
        String selected = "";

        while (!(selected.equals("s") || selected.equals("b"))) {
            System.out.println("s for selling");
            System.out.println("b for buying");
            selected = input.next();
            selected = selected.toLowerCase();
        }
        if (selected.equals("s")) {
            return sell;
        } else {
            return buy;
        }
    }

}
