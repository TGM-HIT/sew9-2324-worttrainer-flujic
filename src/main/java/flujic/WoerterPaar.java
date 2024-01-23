package flujic;

import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.*;


public class WoerterPaar {

    private String wort;
    private String url;


    public WoerterPaar(String wort, String url) throws IOException {
        setWort(wort);
        setUrl(url);

    }

    public void setWort(String wort) {
        this.wort = wort;
    }

    public String getWort() {
        return wort;
    }

    public void setUrl(String url) throws IllegalArgumentException, IOException {

        Image image = ImageIO.read(new URL(url));
        if(image != null){
            this.url = url;
        }else{
            throw new IllegalArgumentException("URL is not an image!");
        }
    }

    public String getUrl() {
        return url;
    }

    public String toString() {
        return this.wort + ";" + url;
    }
}
