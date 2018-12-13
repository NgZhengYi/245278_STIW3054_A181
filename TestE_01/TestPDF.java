import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

public class TestPDF implements Callable<ObjectPDF>, TestPDFInterface {
    private String path, fileName;

    TestPDF(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
    }

    @Override
    public ObjectPDF call() throws Exception {
        ObjectPDF objectPDF = new ObjectPDF();
        String text = textScraping();
        int wordsNumber = countWords(text);
        int charactersNumber = countTotalCharacters(text);
        HashMap<Character, Integer> charactersHashMap = countCharacters(text);

        objectPDF.setPDFObject(fileName, wordsNumber, charactersNumber, charactersHashMap);
        return objectPDF;
    }

    @Override
    public String textScraping() {
        String text = null;
        try (PDDocument pdDocument = PDDocument.load(new File(path))) {
            if (!pdDocument.isEncrypted()) {
                PDFTextStripper pdfTextStripper = new PDFTextStripper();
                text = pdfTextStripper.getText(pdDocument);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    @Override
    public int countWords(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text);
        return tokenizer.countTokens();
    }

    @Override
    public int countTotalCharacters(String text) {
        final String totalLetter = text.replaceAll("[^\\p{L}]", "");
        return totalLetter.length();
    }

    @Override
    public HashMap<Character, Integer> countCharacters(String text) {
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        char[] chars = text.toUpperCase().toCharArray();

        for (char c : chars){
            if (c >= 'A' && c <= 'Z') {
                if (characterIntegerHashMap.containsKey(c)){
                    characterIntegerHashMap.put(c, characterIntegerHashMap.get(c) + 1);
                } else {
                    characterIntegerHashMap.put(c, 1);
                }
            }
        }
        return characterIntegerHashMap;
    }

}