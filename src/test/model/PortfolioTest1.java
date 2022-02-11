package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class PortfolioTest1 {
    private Portfolio portfolio1;


    @BeforeEach
    void runBefore() {
        portfolio1 = new Portfolio();
    }

    @Test
    void testConstructor() {
        assertEquals(0, portfolio1.getSize());
        //assertNotNull(portfolio1.getInvestments());
    }
    @Test
    void testAddToPortfolioNoKey() {
        portfolio1.addToPortfolio("Tesla", 30.00, 500.00);
        assertEquals(1, portfolio1.getSize());
        assertTrue(portfolio1.containsKey("Tesla"));
        assertFalse(portfolio1.containsKey("Apple"));
        // check price, amountfunded and stuff not the arraylist
        //assertEquals(); // I want to test that the Arraylist now contains an Invest with given param

    }

    @Test
    void testAddToPortfolioWithKey() {
        portfolio1.addToPortfolio("Tesla", 30.00, 500.00);
        assertEquals(1, portfolio1.getSize());
        assertTrue(portfolio1.containsKey("Tesla"));
        portfolio1.addToPortfolio("Apple", 20.00, 2000.00);
        assertEquals(2, portfolio1.getSize());
        assertTrue(portfolio1.containsKey("Tesla"));
        assertTrue(portfolio1.containsKey("Apple"));
        portfolio1.addToPortfolio("Apple", 20.00, 2000.00);
        assertEquals(2,portfolio1.getSize());


    }

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
