package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Portfolio {
    private ArrayList<Invest> investments;
    private HashMap<String, ArrayList<Invest>> portfolio;
    // i dont need to name the arraylist since  hashmap.get will give us value (which is the arraylist)

    //EFFECTS: constructs a hashmap called portfolio which contains stock ticker
    //        as the key and list of investments for that ticker as value
    public Portfolio() {
        portfolio = new HashMap<String, ArrayList<Invest>>();

    }


    //MODIFIES: this
    //EFFECTS: if given string name is not a key in portfolio, add it as key and make new
    //         invest with given string name, listedPrice and amountFunded. Else add new invest
    //         to list of investments
    // ticker of given string name as key
    public void addToPortfolio(String name, double listedPrice, double amountFunded) {
              if (portfolio.containsKey(name)) {
                  investments = portfolio.get(name);
                  investments.add(new Invest(name, listedPrice, amountFunded));
              } else {
                  investments = new ArrayList<Invest>();
                  investments.add(new Invest(name, listedPrice, amountFunded));
                  portfolio.put(name, investments);
        }
    }


    //REQUIRES: name must be in Portfolio && Index must be associated to List
    //MODIFIES: this, Invest
    //EFFECTS: sell an Invest at given index in Investments (list) with given name, at given currentPrice and
    //         percentage of shares wanted to be sold
    public void sellInvestInPortfolio(String name, double currentPrice, double percentage, int index) {
        investments = portfolio.get(name);
        investments.get(index).sell(currentPrice, percentage);
    }




    public ArrayList<Invest> getInvestments(String name) {
        return portfolio.get(name);
    }

    public Set<String> keySet() {
        return portfolio.keySet();
    }


    //Do i need these methods (keySet) even tho they are native methods for hashmap

    //returns size of hashmap
    public int getSize() {
        return portfolio.size();
    }

    public boolean isEmpty() {
        return portfolio.isEmpty();
    }



    public boolean containsKey(String name) {
        return portfolio.containsKey(name);
    }

    // containsValue as well??




}