import java.util.HashMap;

public interface TestPDFInterface {
    public String textScraping();
    public int countWords(String t);
    public int countTotalCharacters(String t);
    public HashMap<Character, Integer> countCharacters(String t);
}
