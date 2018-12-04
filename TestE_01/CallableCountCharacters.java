import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class CallableCountCharacters implements Callable<HashMap<Character, Integer>> {
    private String path, text;

    CallableCountCharacters(String path){
        this.path = path;
    }

    @Override
    public HashMap<Character, Integer> call() throws Exception {
        HashMap<Character, Integer> characterHashMap = new HashMap<>();

        extractText();

        char[] charsArray = text.toUpperCase().toCharArray();

        for (char c : charsArray) {
            if (c >= 'A' && c <= 'Z') {
                if (characterHashMap.containsKey(c)) {
                    characterHashMap.put(c, characterHashMap.get(c) + 1);
                } else {
                    characterHashMap.put(c, 1);
                }
            }
        }

        return characterHashMap;
    }

    public void extractText() throws IOException {
        File file = new File(path);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper textStripper = new PDFTextStripper();
        text = textStripper.getText(document);
        document.close();
    }
}
