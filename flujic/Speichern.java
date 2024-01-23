package flujic;

import java.io.IOException;
public interface Speichern {

    void saveContent(String filePath, WortTrainer wordTrainer) throws IOException;
    WortTrainer loadContent(String filePath, WortTrainer wordTrainer);
}

