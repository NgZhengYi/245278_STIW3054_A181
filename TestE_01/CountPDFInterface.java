import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public interface CountPDFInterface {
    public AtomicInteger calculateTotalWords();
    public AtomicInteger calculateTotalCharacters();
    public HashMap<Character, Integer> charactersHashMap();
}
