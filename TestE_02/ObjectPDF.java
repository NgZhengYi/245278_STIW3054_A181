import java.util.HashMap;

public class ObjectPDF {
    private String fileName;
    private int wordsNumber;
    private HashMap<Character, Integer> characterHashMap;

    public String getFileName() {
        return fileName;
    }

    public int getWordsNumber() {
        return wordsNumber;
    }

    public HashMap<Character, Integer> getCharacterHashMap() {
        return characterHashMap;
    }

    public void setPDFObject(String fileName, int wordsNumber, HashMap<Character, Integer> characterIntegerHashMap){
        this.fileName = fileName;
        this.wordsNumber = wordsNumber;
        this.characterHashMap = characterIntegerHashMap;
    }
}
