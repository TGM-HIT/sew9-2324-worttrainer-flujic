package flujic;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

//Kernlogik des Spiels, Verwaltung der Spielzustände.
public class WortTrainer {
    // Startpunkt des Programms.
    public static void main(String[] args) throws IOException {
        WortTrainer wt = new WortTrainer("src/main/resources/words.json");
        wt.play();
    }
    private ArrayList<WoerterPaar> woerterPaare; // Liste der Wort-Bild-Paare
    private Speichern speichern; // Speichermechanismus (hier JSON)
    private int index; // Aktueller Index im Wort-Bild-Paar-Array
    private int richtige; // Anzahl richtiger Antworten
    private int falsche; // Anzahl falscher Antworten
    private int versuche; // Anzahl gesamter Versuche
    private String pfad; // Pfad zur Speicherdatei

    // Konstruktor der Klasse, der den Dateipfad entgegennimmt
    public WortTrainer(String s) throws IOException {
        //änderung 1
        this.pfad = s;

        this.speichern = new JSONSave();
        this.woerterPaare = new ArrayList<WoerterPaar>();
        this.index = 0;
        this.richtige = 0;
        this.falsche = 0;
        this.versuche = 0;
        // Hier können Wort-Bild-Paare hinzugefügt werden.
        //this.woerterPaare.add(new WoerterPaar("https://www.lucypetproducts.com/wp-content/uploads/2020/01/Golden4.jpg", "Hund"));
        //this.woerterPaare.add(new WoerterPaar("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_November_2010-1a.jpg/640px-Cat_November_2010-1a.jpg", "Katze"));
        //this.woerterPaare.add(new WoerterPaar("https://www.wissenschaft.de/wp-content/uploads/2/0/2023-11-seeadler.jpg", "Adler"));

        // this.woerterPaare.add(new WoerterPaar("Hund", "https://www.lucypetproducts.com/wp-content/uploads/2020/01/Golden4.jpg"));
        // this.woerterPaare.add(new WoerterPaar("Katze", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Cat_November_2010-1a.jpg/640px-Cat_November_2010-1a.jpg"));
        // this.woerterPaare.add(new WoerterPaar("Adler", "https://www.wissenschaft.de/wp-content/uploads/2/0/2023-11-seeadler.jpg"));
    }


    public void play(){
        this.load(); // Laden der gespeicherten Daten
        // Zurücksetzen und Mischen der Wort-Bild-Paare.
        if(index >= this.woerterPaare.size()) this.zuruecksetzen();
        if(index == 0) randomize();

        // Spiel-Schleife
        while(index < this.woerterPaare.size()){
            Image img = getImage();

            String res;
            do {
                res = (String) JOptionPane.showInputDialog(null, "Was ist das Wort?", "Wort Trainer", JOptionPane.QUESTION_MESSAGE, new ImageIcon(img), null, null);
                if(res == null || res.isEmpty()){
                    return;
                }
                // Überprüfung, ob die Eingabe nur Buchstaben enthält
                if(!res.matches("[a-zA-ZäöüÄÖÜß]+")) {
                    JOptionPane.showMessageDialog(null, "Bitte nur Buchstaben eingeben!");
                    res = "";
                }
            } while(res.isEmpty());

            // Überprüfung der Eingabe
            boolean guessed = res.equalsIgnoreCase(getPaar(this.index).getWort());
            if(guessed){
                this.richtige++;
                this.index++;
            } else {
                this.falsche++;
            }
            this.versuche++;
            JOptionPane.showMessageDialog(null, (guessed ? "Richtig!" : "Falsch!") + "\nVersuche: " + versuche + "\nRichtig: " + richtige + "\nFalsch: " + falsche);
        }

        // Speichern oder Zurücksetzen des Spielstandes am Ende des Spiels
        String antwort = JOptionPane.showInputDialog(null,"Willst du deinen Spielstand speichern?");
        if (antwort.equals("ja")){
            this.save();
        } else {
            zuruecksetzen();
            this.versuche = 0;
            this.richtige = 0;
            this.falsche = 0;
            this.save();
        }
    }


    // Zurücksetzen der Spielstatistik
    public void zuruecksetzen(){
        this.index = 0;
        randomize();
    }

    // Mischen der Wort-Bild-Paare
    private void randomize(){
        for(int i = 0; i < this.woerterPaare.size(); i++){
            int randomI = (int) (Math.random() * this.woerterPaare.size());
            WoerterPaar temp = this.woerterPaare.get(randomI);
            this.woerterPaare.set(randomI, this.woerterPaare.get(i));
            this.woerterPaare.set(i, temp);
        }

    }

    // Abrufen des Bildes aus dem aktuellen Wort-Bild-Paar
    private Image getImage(){
        Image img = null;
        System.out.println(getPaar(this.index).getUrl());
        try {
            URL url = new URL(getPaar(this.index).getUrl());
            img = ImageIO.read(url);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return img;
    }

    // Laden der Spielstände
    public void load(){
        WortTrainer geladen = this.speichern.loadContent(this.pfad, this);

            this.woerterPaare = geladen.getWoerterPaare();
            this.index = geladen.getIndex();
            this.richtige = geladen.getRichtige();
            this.falsche = geladen.getFalsche();
            this.versuche = geladen.getVersuche();

    }

    // Speichern der Spielstände
    public void save(){
        try {
            this.speichern.saveContent(this.pfad, this);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    // Getter- und Setter-Methoden für verschiedene Felder
    public ArrayList<WoerterPaar> getWoerterPaare() {
        return woerterPaare;
    }

    public void setWoerterPaare(ArrayList<WoerterPaar> woerterPaare) {
        this.woerterPaare = woerterPaare;
    }

    public Speichern getSpeichern() {
        return speichern;
    }

    public void setSpeichern(Speichern speichern) {
        this.speichern = speichern;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getRichtige() {
        return richtige;
    }

    public void setRichtige(int richtige) {
        this.richtige = richtige;
    }

    public int getFalsche() {
        return falsche;
    }

    public void setFalsche(int falsche) {
        this.falsche = falsche;
    }

    public int getVersuche() {
        return versuche;
    }

    public void setVersuche(int versuche) {
        this.versuche = versuche;
    }

    public String getPfad() {
        return pfad;
    }

    public void setPfad(String pfad) {
        this.pfad = pfad;
    }

    public void hinzu(WoerterPaar woerterPaar){
        this.woerterPaare.add(woerterPaar);
    }

    public WoerterPaar getPaar(int index){
        return this.woerterPaare.get(index);
    }

    public void loeschen(){
        this.woerterPaare.clear();
    }

}
