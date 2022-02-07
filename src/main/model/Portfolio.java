package model;

import java.util.ArrayList;

public class Portfolio {
    private ArrayList<Invest> investments;

    public Portfolio() {
        investments = new ArrayList<>();
    }

    public void addInvestment(Invest invest) {
        investments.add(invest);
    }

    public void sellInvestment(Invest invest) {
        investments.
    }

    public ArrayList<Invest> getInvestments() {
        return investments;
    }
}
