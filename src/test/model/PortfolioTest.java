package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;



public class PortfolioTest {
    private Portfolio portfolio1;

    @BeforeEach
    void runBefore() {
        portfolio1 = new Portfolio();
    }

    @Test
    void testConstructor() {
        assertEquals(0, portfolio1.getSize());
        assertTrue(portfolio1.isEmpty());
        assertNotNull(portfolio1.keySet());
        assertFalse(portfolio1.containsKey("Tesla"));
        assertFalse(portfolio1.containsKey("Apple"));

    }


    @Test
    void testAddToPortfolioNoKey() {
        portfolio1.addToPortfolio("Tesla", 30.00, 500.00);
       // ArrayList<Invest> investments = new ArrayList<>();
        //assertEquals(investments.get(0), portfolio1.getInvestments("Tesla").get(0));



        assertEquals(1, portfolio1.getSize());
        assertTrue(portfolio1.containsKey("Tesla"));
        assertFalse(portfolio1.containsKey("Apple"));
        assertNotNull(portfolio1.getInvestments("Tesla"));



        // check price, amountfunded and stuff not the arraylist
        //assertEquals(); // I want to test that the Arraylist now contains an Invest with given param

    }

    // need tests
    // add to portfolio -> get arraylist of investments ->

    @Test
    void testAddToPortfolioWithKey() {
        portfolio1.addToPortfolio("Apple", 20.00, 2000.00);
        ArrayList<Invest> investments;
        investments = portfolio1.getInvestments("Apple");
        assertEquals(investments, portfolio1.getInvestments("Apple"));
        assertEquals("Apple", portfolio1.getInvestments("Apple").get(0).getName());
        assertEquals(2000.00, portfolio1.getInvestments("Apple").get(0).getAmountFunded());
        assertEquals(20.00, portfolio1.getInvestments("Apple").get(0).getListPrice());
        assertEquals(2000.00/20.00, portfolio1.getInvestments("Apple").get(0).getNumberShares());
        assertEquals(2000.00, portfolio1.getInvestments("Apple").get(0).getBalance());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(0).getProfit());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(0).getRealizedGains());


        portfolio1.addToPortfolio("Apple", 30.00, 6000.00);
        assertEquals(investments, portfolio1.getInvestments("Apple"));
    }

    /*
        assertTrue(portfolio1.containsKey("Tesla"));
        assertTrue(portfolio1.containsKey("Apple"));

        assertEquals(2, portfolio1.getInvestments("Apple").size());
        assertEquals(1, portfolio1.getInvestments("Tesla").size());

        // how do i check that the arraylist has the invest object added to it since I think thats why i cant cover
        // my if statements


        assertEquals("Tesla", portfolio1.getInvestments("Tesla").get(0).getName());
        assertEquals(500, portfolio1.getInvestments("Tesla").get(0).getAmountFunded());
        assertEquals(30.00, portfolio1.getInvestments("Tesla").get(0).getListPrice());
        assertEquals(500.00/30.00, portfolio1.getInvestments("Tesla").get(0).getNumberShares());
        assertEquals(500.00, portfolio1.getInvestments("Tesla").get(0).getBalance());
        assertEquals(0.0, portfolio1.getInvestments("Tesla").get(0).getProfit());
        assertEquals(0.0, portfolio1.getInvestments("Tesla").get(0).getRealizedGains());

        assertEquals("Apple", portfolio1.getInvestments("Apple").get(0).getName());
        assertEquals(2000.00, portfolio1.getInvestments("Apple").get(0).getAmountFunded());
        assertEquals(20.00, portfolio1.getInvestments("Apple").get(0).getListPrice());
        assertEquals(2000.00/20.00, portfolio1.getInvestments("Apple").get(0).getNumberShares());
        assertEquals(2000.00, portfolio1.getInvestments("Apple").get(0).getBalance());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(0).getProfit());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(0).getRealizedGains());

        assertEquals("Apple", portfolio1.getInvestments("Apple").get(1).getName());
        assertEquals(6000.00, portfolio1.getInvestments("Apple").get(1).getAmountFunded());
        assertEquals(30.00, portfolio1.getInvestments("Apple").get(1).getListPrice());
        assertEquals(6000.00/30.00, portfolio1.getInvestments("Apple").get(1).getNumberShares());
        assertEquals(6000.00, portfolio1.getInvestments("Apple").get(1).getBalance());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(1).getProfit());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(1).getRealizedGains());


        assertEquals(2, portfolio1.getSize());


    }

     */

    @Test
    void testSellInvestInPortfolio() {
        portfolio1.addToPortfolio("Apple", 20.00, 2000.00);
        portfolio1.addToPortfolio("Apple", 10.00, 3000.00);
        portfolio1.sellInvestInPortfolio("Apple", 5.00, 50.0, 0);
        assertEquals(250, portfolio1.getInvestments("Apple").get(0).getRealizedGains());
        portfolio1.sellInvestInPortfolio("Apple", 5.00, 10.0, 1);
        assertEquals(150, portfolio1.getInvestments("Apple").get(1).getRealizedGains());
    }
}
