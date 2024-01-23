package flujic;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
public class WortTrainer {
    public static void main(String[] args) throws IOException {
        WortTrainer wt = new WortTrainer("src/main/resources/words.json");
        wt.play();
    }

    private ArrayList<WoerterPaar> woerterPaare;
    private Speicherung speicherung;
    private int index;
    private int richtige;
    private int falsche;
    private int versuche;
    private String pfad;
    public WortTrainer(String s) {

    }


}
