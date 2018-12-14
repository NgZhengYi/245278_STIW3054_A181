import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class CountCharacters implements CountCharactersInterface {
    private ArrayList<Future<ObjectPDF>> objectPDFArrayList;

    CountCharacters(ArrayList<Future<ObjectPDF>> objectPDFArrayList) {
        this.objectPDFArrayList = objectPDFArrayList;
    }

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

}