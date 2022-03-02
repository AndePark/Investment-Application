package ui;


import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new InvestmentApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to Run Application: file not found");
        }
    }
}
