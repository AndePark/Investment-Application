/*package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PortfolioTest {
    private Portfolio testPortfolio; // test hashmap
    private Investment testInvestment;

    private Invest invest1;
    private Invest invest2;

    @BeforeEach
    void runBefore() {
        testPortfolio = new Portfolio();
        invest1 = new Invest("Tesla", 1000.00, 500.00);
        invest2 = new Invest("Apple", 5000.00, 1000.00);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testPortfolio.getInvestments().size());
    }

    @Test
    void testAddInvestment() {
        testPortfolio.addInvestment(invest1);
        assertEquals(1,testPortfolio.getInvestments().size());
        assertEquals(invest1, testPortfolio.getInvestments().get(0));
    }

    @Test
    void testAddInvestments() {
        testPortfolio.addInvestment(invest1);
        testPortfolio.addInvestment(invest2);
        assertEquals(2, testPortfolio.getInvestments().size());
        assertEquals(invest2, testPortfolio.getInvestments().get(1));
    }
}
*/