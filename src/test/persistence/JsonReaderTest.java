package persistence;

import model.Portfolio;
import org.json.JSONObject;
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
            assertNotNull(pr.keySet());
        } catch (IOException e) {
            fail("couldnt read");
        }
    }

    @Test
    void testReadGeneralPortfolio() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPortfolio.json");
        try {
            Portfolio pr = reader.read();
            assertTrue(pr.containsKey("tesla"));
            assertTrue(pr.containsKey("apple"));
            assertNotNull(pr.getInvestments("tesla"));
            assertEquals(2, pr.getInvestments("tesla").size());
        } catch (IOException e) {
            fail("could not read");
        }
    }
}
