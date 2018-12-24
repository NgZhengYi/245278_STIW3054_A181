import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CountTotalPDF implements CountTotalPDFInterface {
    private ArrayList<Future<ObjectPDF>> objectPDFArrayList;

    CountTotalPDF(ArrayList<Future<ObjectPDF>> objectPDFArrayList) {
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
        HashMap<Character, Integer> totalCharactersHashMap = new HashMap<>();
        objectPDFArrayList.forEach(objectPDFFuture -> {
            try {
                objectPDFFuture.get().getCharacterHashMap().forEach((key, value) -> {
                    totalCharactersHashMap.merge(key, value, Integer::sum);
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        return totalCharactersHashMap;
    }

    @Override
    public ArrayList<Integer> countCharactersLengthList() {
        ArrayList<Integer> totalCharactersLengthList = new ArrayList<>();
        objectPDFArrayList.forEach(objectPDFFuture -> {
            try {
                totalCharactersLengthList.addAll(objectPDFFuture.get().getWordsLengthArrayList());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        return totalCharactersLengthList;
    }
}
