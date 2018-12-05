import java.util.HashMap;
import java.util.concurrent.Callable;

public class CallableCountCharacters implements Callable<HashMap<Character, Integer>> {
    private String text;

    CallableCountCharacters(String text){
        this.text = text;
    }

    @Override
    public HashMap<Character, Integer> call() {
        HashMap<Character, Integer> characterHashMap = new HashMap<>();
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

}
