import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CountWords implements CountWordsInterface {
    private ArrayList<Future<ObjectPDF>> objectPDFArrayList;

    CountWords(ArrayList<Future<ObjectPDF>> objectPDFArrayList) {
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

}