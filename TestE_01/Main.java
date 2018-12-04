import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static ArrayList<String> pathArrayList = new ArrayList<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ArrayList<Future<Integer>> futureIntegerArrayList = new ArrayList<>();
        PDFFilePath();

        for (int i = 0; i < pathArrayList.size(); i++) {
            Future<Integer> futureInteger = executorService.submit(new CallableCountWords(pathArrayList.get(i)));
            futureIntegerArrayList.add(futureInteger);
        }

        AtomicInteger totalWords = new AtomicInteger();
        futureIntegerArrayList.forEach(future -> {
            try {
                System.out.println("Document : " + future.get() + " Words");
                totalWords.addAndGet(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Total Words from Documents : " + totalWords);
        executorService.shutdown();
    }

    public static void PDFFilePath(){
        // PDF Document Path
        
        //pathArrayList.add("");
    }

}
