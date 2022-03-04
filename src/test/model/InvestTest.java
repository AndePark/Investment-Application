package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvestTest {
    private Invest invest1;
    private Invest invest2;
    private Invest invest3;
    private Invest invest4;


    @BeforeEach
    void runBefore() {
        invest1 = new Invest("Tesla", 1000.00, 5000.00);
        invest2 = new Invest("Apple", 300000.00, 500.00);
        invest3 = new Invest("Yahoo", 200, 400,
                2, 0, 0, 0);
        invest4 = new Invest("AMC", 5, 5000,
                500, 7500, 2500, 5000);
    }

    @Test
    void testConstructor() {
        assertEquals("Tesla", invest1.getName());
        assertEquals(1000.00, invest1.getListPrice());
        assertEquals(5000.00, invest1.getAmountFunded());
        assertEquals(5000.00/1000.00, invest1.getNumberShares());
        assertEquals(0, invest1.getProfit());
        assertEquals(0, invest1.getRealizedGains());
    }

    @Test
    void testConstructorTwo() {
        assertEquals("Apple", invest2.getName());
        assertEquals(300000.00, invest2.getListPrice());
        assertEquals(500.00, invest2.getAmountFunded());
        assertEquals(500.00/300000.00, invest2.getNumberShares());
        assertEquals(0, invest2.getProfit());
    }

    @Test
    void testConstructorThree() {
        assertEquals("Yahoo", invest3.getName());
        assertEquals(200.00, invest3.getListPrice());
        assertEquals(400, invest3.getAmountFunded());
        assertEquals(400.00/200.00, invest3.getNumberShares());
        assertEquals(0, invest3.getBalance());
        assertEquals(0, invest3.getProfit());
        assertEquals(0, invest3.getRealizedGains());
    }

    @Test
    void testConstructorFour() {
        assertEquals("AMC", invest4.getName());
        assertEquals(5, invest4.getListPrice());
        assertEquals(5000, invest4.getAmountFunded());
        assertEquals(500, invest4.getNumberShares());
        assertEquals(7500, invest4.getBalance());
        assertEquals(2500, invest4.getProfit());
        assertEquals(5000, invest4.getRealizedGains());
    }

    @Test
    void testSell() {
        invest1.sell(500.00,50);
        assertEquals(1250.0, invest1.getRealizedGains());
        assertEquals(2.5, invest1.getNumberShares());
        assertEquals(3750,invest1.getBalance());
        assertEquals(-1250, invest1.getProfit());
        invest1.sell(5000, 10);
        assertEquals(2500,invest1.getRealizedGains());
        assertEquals(2.25, invest1.getNumberShares());
        assertEquals(4750, invest1.getBalance());
        assertEquals(-250, invest1.getProfit());
    }
}


