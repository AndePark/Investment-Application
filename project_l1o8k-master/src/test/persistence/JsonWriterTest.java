package persistence;

import model.Portfolio;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// referenced JsonTutorial.JsonWriterTest for testWriterInvalidFile() && testWriterEmptyPortfolio()/ took inspiration
// for testWriterGeneralPortfolio()
// model: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Portfolio pr = new Portfolio();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyPortfolio() {
        try {
            Portfolio pr = new Portfolio();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPortfolio.json");
            writer.open();
            writer.write(pr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPortfolio.json");
            pr = reader.read();
            assertNotNull(pr.keySet());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralPortfolio() {
        try {
            Portfolio pr = new Portfolio();
            pr.addToPortfolio("tesla", 1000, 3000,3, 3000, 0, 0 );
            pr.addToPortfolio("tesla", 2000, 4000, 2, 4000, 0, 0);
            pr.addToPortfolio("apple", 100, 500, 4, 450, -50, 50 );
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPortfolio.json");
            writer.open();
            writer.write(pr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPortfolio.json");
            pr = reader.read();
            assertEquals(2, pr.getSize());

            checkInvestments("tesla", 1000, 3000,3, 3000,
                    0, 0, pr.getInvestments("tesla").get(0));
            checkInvestments("tesla", 2000, 4000, 2, 4000,
                    0, 0, pr.getInvestments("tesla").get(1));
            checkInvestments("apple", 100, 500, 4, 450,
                    -50, 50, pr.getInvestments("apple").get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
