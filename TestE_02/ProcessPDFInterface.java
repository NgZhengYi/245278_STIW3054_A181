import java.util.ArrayList;
import java.util.HashMap;

public interface ProcessPDFInterface {
    public String textScraping();
    public int countWords(String t);
    public int countTotalCharacters(String t);
    public HashMap<Character, Integer> countCharacters(String t);
    public ArrayList<Integer> countCharactersLengthList(String t);
}