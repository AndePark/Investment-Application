package persistence;

import model.Portfolio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

// referenced JsonTutorial.JsonReaderTest for testReaderNonExistentFile() && testReaderEmptyPortfolio()/ took inspiration
// for testReadGeneralPortfolio()
// model: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");

        try {
            Portfolio pr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPortfolio.json");
        try {
            Portfolio pr = reader.read();
            assertEquals(0, pr.getSize());
            assertTrue(pr.isEmpty());
        } catch (IOException e) {
            fail("couldn't read");
        }
    }

    @Test
    void testReadGeneralPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPortfolio.json");
        try {
            Portfolio pr = reader.read();
            assertTrue(pr.containsKey("tesla"));
            assertTrue(pr.containsKey("apple"));
            assertEquals(2, pr.getSize());
            assertEquals(2, pr.getInvestments("tesla").size());

            checkInvestments("tesla", 1000.00, 200.00, 5.00, 200.40,
                    1000.00, 4000.00, pr.getInvestments("tesla").get(0));
            checkInvestments("tesla", 2500.00, 300.00, 23.00, 23.40,
                    100.00, 7000, pr.getInvestments("tesla").get(1));
            assertEquals(1, pr.getInvestments("apple").size());
            checkInvestments("apple", 5000.00, 20.00, 1.00, 2320.40,
                    10000.00, 4100, pr.getInvestments("apple").get(0));

        } catch (IOException e) {
            fail("could not read");
        }
    }
}
