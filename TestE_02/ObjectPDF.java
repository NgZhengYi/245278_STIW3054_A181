import java.util.ArrayList;
import java.util.HashMap;

public class ObjectPDF {
    private String fileName;
    private int wordsNumber, charactersNumber;
    private HashMap<Character, Integer> characterHashMap;
    private ArrayList<Integer> wordsLengthArrayList;

    public String getFileName() {
        return fileName;
    }

    public int getWordsNumber() {
        return wordsNumber;
    }

    public int getCharactersNumber() {
        return charactersNumber;
    }

    public HashMap<Character, Integer> getCharacterHashMap() {
        return characterHashMap;
    }

    public ArrayList<Integer> getWordsLengthArrayList() {
        return wordsLengthArrayList;
    }

    public void setPDFObject(String fileName, int wordsNumber, int charactersNumber,
                             HashMap<Character, Integer> characterIntegerHashMap, ArrayList<Integer> wordsLengthArrayList) {
        this.fileName = fileName;
        this.wordsNumber = wordsNumber;
        this.charactersNumber = charactersNumber;
        this.characterHashMap = characterIntegerHashMap;
        this.wordsLengthArrayList = wordsLengthArrayList;
    }
}
