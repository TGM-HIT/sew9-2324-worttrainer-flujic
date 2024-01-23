package flujic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WortTrainerTest {

    private WortTrainer wortTrainer;

    @BeforeEach
    public void setup() throws IOException {
        this.wortTrainer = new WortTrainer("src/test/resources/words.json");
        this.wortTrainer.loeschen();
        this.wortTrainer.zuruecksetzen();
    }

    @Test
    public void testWordCountZero() {
        assertEquals(0, this.wortTrainer.getWoerterPaare().size());
    }

    @Test
    public void testWordCountOne() {
        try {
            this.wortTrainer.hinzu(new WoerterPaar("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/368px-Google_2015_logo.svg.png", "Google"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, this.wortTrainer.getWoerterPaare().size());
    }

    @Test
    public void testSerialization() throws IOException {
        try {
            this.wortTrainer.hinzu(new WoerterPaar("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/368px-Google_2015_logo.svg.png", "Google"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.wortTrainer.save();
        WortTrainer wortTrainer = new WortTrainer("src/test/resources/words.json");
        assertEquals("Google", wortTrainer.getPaar(0).getWort());
    }

}