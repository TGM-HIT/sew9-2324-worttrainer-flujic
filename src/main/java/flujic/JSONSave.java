package flujic;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.io.IOException;

public class JSONSave implements Speichern{


    @Override
    public void saveContent(String filePath, WortTrainer wordTrainer) throws IOException {
        if(!new File(filePath).exists()) new File(filePath).createNewFile();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("wortPaare", wordTrainer.getWoerterPaare());
        jsonObject.put("index", wordTrainer.getIndex());
        jsonObject.put("richtige", wordTrainer.getRichtige());
        jsonObject.put("falsche", wordTrainer.getFalsche());
        jsonObject.put("versuche", wordTrainer.getVersuche());

        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(jsonObject.toString());
        fileWriter.close();
    }

    @Override
    public WortTrainer loadContent(String filePath, WortTrainer wordTrainer) {
        if(!new File(filePath).exists()) return null;
        File file = new File(filePath);

        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));

            JSONObject jsonObject = new JSONObject(content);
            wordTrainer.setWoerterPaare(convertJSONArrayToArrayList(jsonObject.getJSONArray("wortPaare")));
            wordTrainer.setIndex(jsonObject.getInt("index"));
            wordTrainer.setRichtige(jsonObject.getInt("richtige"));
            wordTrainer.setFalsche(jsonObject.getInt("falsche"));
            wordTrainer.setVersuche(jsonObject.getInt("versuche"));

            return wordTrainer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<WoerterPaar> convertJSONArrayToArrayList(JSONArray jsonArray) {
        ArrayList<WoerterPaar> woerterPaar = new ArrayList<WoerterPaar>();
        for(int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            try {
                woerterPaar.add(new WoerterPaar(jsonObject.getString("url"), jsonObject.getString("wort")));
            } catch (IllegalArgumentException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return woerterPaar;
    }

}
