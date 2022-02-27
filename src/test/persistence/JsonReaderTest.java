package persistence;

import model.Portfolio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

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
            assertEquals(1, pr.getInvestments("apple").size());
        } catch (IOException e) {
            fail("could not read");
        }
    }
}
