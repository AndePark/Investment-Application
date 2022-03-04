package persistence;

import model.Invest;

// referenced JsonTutorial.JsonTest for checkInvestments()
// model: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkInvestments(String name, double listedPrice, double amountFunded, double numberShares,
                                    double balance, double profit, double realizedGains,
                                    Invest investment) {
        assertEquals(name, investment.getName());
        assertEquals(listedPrice, investment.getListPrice());
        assertEquals(amountFunded, investment.getAmountFunded());
        assertEquals(numberShares, investment.getNumberShares());
        assertEquals(balance, investment.getBalance());
        assertEquals(profit, investment.getProfit());
        assertEquals(realizedGains,investment.getRealizedGains());
    }

}
