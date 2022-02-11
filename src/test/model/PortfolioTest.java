package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        assertEquals("Tesla", portfolio1.getInvestments("Tesla").get(0).getName());
        assertEquals(500.00, portfolio1.getInvestments("Tesla").get(0).getAmountFunded());
        assertEquals(30.00, portfolio1.getInvestments("Tesla").get(0).getListPrice());
        assertEquals(500.00/30.00, portfolio1.getInvestments("Tesla").get(0).getNumberShares());
        assertEquals(500.00, portfolio1.getInvestments("Tesla").get(0).getBalance());
        assertEquals(0.0, portfolio1.getInvestments("Tesla").get(0).getProfit());
        assertEquals(0.0, portfolio1.getInvestments("Tesla").get(0).getRealizedGains());
        assertEquals(1, portfolio1.getSize());
        assertTrue(portfolio1.containsKey("Tesla"));
        assertFalse(portfolio1.containsKey("Apple"));
        assertNotNull(portfolio1.getInvestments("Tesla"));
    }

    @Test
    void testAddToPortfolioWithKey() {
        portfolio1.addToPortfolio("Apple", 20.00, 2000.00);
        assertEquals("Apple", portfolio1.getInvestments("Apple").get(0).getName());
        assertEquals(2000.00, portfolio1.getInvestments("Apple").get(0).getAmountFunded());
        assertEquals(20.00, portfolio1.getInvestments("Apple").get(0).getListPrice());
        assertEquals(2000.00/20.00, portfolio1.getInvestments("Apple").get(0).getNumberShares());
        assertEquals(2000.00, portfolio1.getInvestments("Apple").get(0).getBalance());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(0).getProfit());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(0).getRealizedGains());


        portfolio1.addToPortfolio("Apple", 30.00, 6000.00);
        assertEquals("Apple", portfolio1.getInvestments("Apple").get(1).getName());
        assertEquals(6000.00, portfolio1.getInvestments("Apple").get(1).getAmountFunded());
        assertEquals(30.00, portfolio1.getInvestments("Apple").get(1).getListPrice());
        assertEquals(6000.00/30.00, portfolio1.getInvestments("Apple").get(1).getNumberShares());
        assertEquals(6000.00, portfolio1.getInvestments("Apple").get(1).getBalance());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(1).getProfit());
        assertEquals(0.0, portfolio1.getInvestments("Apple").get(1).getRealizedGains());

        portfolio1.addToPortfolio("Tesla", 30.00, 500.00);
        assertEquals("Tesla", portfolio1.getInvestments("Tesla").get(0).getName());
        assertEquals(500, portfolio1.getInvestments("Tesla").get(0).getAmountFunded());
        assertEquals(30.00, portfolio1.getInvestments("Tesla").get(0).getListPrice());
        assertEquals(500.00/30.00, portfolio1.getInvestments("Tesla").get(0).getNumberShares());
        assertEquals(500.00, portfolio1.getInvestments("Tesla").get(0).getBalance());
        assertEquals(0.0, portfolio1.getInvestments("Tesla").get(0).getProfit());
        assertEquals(0.0, portfolio1.getInvestments("Tesla").get(0).getRealizedGains());

        assertTrue(portfolio1.containsKey("Tesla"));
        assertTrue(portfolio1.containsKey("Apple"));

        assertEquals(2, portfolio1.getInvestments("Apple").size());
        assertEquals(1, portfolio1.getInvestments("Tesla").size());
        assertEquals(2, portfolio1.getSize());
    }


    @Test
    void testSellInvestInPortfolio() {
        portfolio1.addToPortfolio("Apple", 20.00, 2000.00);
        portfolio1.addToPortfolio("Apple", 10.00, 3000.00);
        portfolio1.sellInvestInPortfolio("Apple", 5.00, 50.0, 0);
        assertEquals(250, portfolio1.getInvestments("Apple").get(0).getRealizedGains());
        assertEquals(50.00, portfolio1.getInvestments("Apple").get(0).getNumberShares());
        assertEquals(1250.00, portfolio1.getInvestments("Apple").get(0).getBalance());
        assertEquals(-750.00, portfolio1.getInvestments("Apple").get(0).getProfit());

        portfolio1.sellInvestInPortfolio("Apple", 50.00, 10.0, 1);
        assertEquals(1500.0, portfolio1.getInvestments("Apple").get(1).getRealizedGains());
        assertEquals(270.00, portfolio1.getInvestments("Apple").get(1).getNumberShares());
        assertEquals(4200.00, portfolio1.getInvestments("Apple").get(1).getBalance());
        assertEquals(1200.00, portfolio1.getInvestments("Apple").get(1).getProfit());
    }
}
