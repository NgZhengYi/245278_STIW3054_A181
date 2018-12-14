import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CharactersHashMap implements CharacterHashMapInterface {
    private ArrayList<Future<ObjectPDF>> objectPDFArrayList;

    CharactersHashMap(ArrayList<Future<ObjectPDF>> objectPDFArrayList) {
        this.objectPDFArrayList = objectPDFArrayList;
    }

    @Override
    public HashMap<Character, Integer> charactersHashMap() {
        HashMap<Character, Integer> totalCharectersHashMap = new HashMap<>();
        for (int i = 0; i < objectPDFArrayList.size(); i++) {
            try {
                objectPDFArrayList.get(i).get().getCharacterHashMap().forEach((key, value) -> {
                    totalCharectersHashMap.merge(key, value, Integer::sum);
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return totalCharectersHashMap;
    }

}