import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public interface CountTotalPDFInterface {
    public AtomicInteger calculateTotalWords();
    public AtomicInteger calculateTotalCharacters();
    public HashMap<Character, Integer> charactersHashMap();
    public ArrayList<Integer> countCharactersLengthList();
}
