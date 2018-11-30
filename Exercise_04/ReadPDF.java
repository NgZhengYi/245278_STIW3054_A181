import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ReadPDF {
    private String text;

    public synchronized void extractText(String path) throws IOException {
        // Load File
        File file = new File(path);
        try (PDDocument pdDocument = PDDocument.load(file)){
            pdDocument.getClass();

            if (!pdDocument.isEncrypted()){
                PDFTextStripper pdfTextStripper = new PDFTextStripper();
                text = pdfTextStripper.getText(pdDocument);
            }
        }
    }

    public void calculateText() {
        //System.out.println(text);
        System.out.println("Number of Words : " + countWords(text));
        //System.out.println("Number of Characters : " + countCharacters(text));
        countCharacterTypes(text);
    }

    public int countWords(String text) {
        if (text == null || text.isEmpty()){
            return 0;
        }

        //String [] words = text.split("\\s+");
        StringTokenizer tokenizer = new StringTokenizer(text);
        //return words.length;
        return tokenizer.countTokens();
    }

    public void countCharacterTypes(String text) {
        HashMap<Character, Integer> charactersMap = new HashMap<Character, Integer>();

        char[] charArray = text.toCharArray();

        for (char c : charArray){
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ){
                if (charactersMap.containsKey(c)){
                    charactersMap.put(c, charactersMap.get(c) + 1);
                } else {
                    charactersMap.put(c, 1);
                }
            }
        }
        charactersMap.forEach((key, value) -> System.out.println(key + " : " + value));
    }

}
