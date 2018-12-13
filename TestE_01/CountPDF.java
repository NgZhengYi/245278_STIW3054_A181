import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CountPDF implements CountPDFInterface {
    private ArrayList<Future<ObjectPDF>> objectPDFArrayList;

    CountPDF(ArrayList<Future<ObjectPDF>> objectPDFArrayList) {
        this.objectPDFArrayList = objectPDFArrayList;
    }

    @Override
    public AtomicInteger calculateTotalWords() {
        AtomicInteger totalWords = new AtomicInteger();
        objectPDFArrayList.forEach(future -> {
            try {
                totalWords.addAndGet(future.get().getWordsNumber());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        return totalWords;
    }

    @Override
    public AtomicInteger calculateTotalCharacters() {
        AtomicInteger totalCharacters = new AtomicInteger();
        objectPDFArrayList.forEach(future -> {
            try {
                totalCharacters.addAndGet(future.get().getCharactersNumber());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        return totalCharacters;
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