import java.util.ArrayList;
import java.util.HashMap;
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
        ArrayList<Future<HashMap<Character, Integer>>> futureHashMapArrayList = new ArrayList<>();

        PDFFilePath();

        for (int i = 0; i < pathArrayList.size(); i++) {
            CallableCountWords callableCountWords = new CallableCountWords(pathArrayList.get(i));
            Future<Integer> futureInteger = executorService.submit(callableCountWords);
            futureIntegerArrayList.add(futureInteger);

            CallableCountCharacters callableCountCharacters = new CallableCountCharacters(pathArrayList.get(i));
            Future<HashMap<Character, Integer>> futureHashMap = executorService.submit(callableCountCharacters);
            futureHashMapArrayList.add(futureHashMap);
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
        System.out.println("\n$---------- Total Characters ----------$");

        for (int i = 0; i < futureHashMapArrayList.size(); i++){
            try {
                System.out.println(futureHashMapArrayList.get(i).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        HashMap<Character, Integer> totalCharactersHashMap = new HashMap<>();

        for (int j = 0; j < futureHashMapArrayList.size(); j++) {
            try {
                futureHashMapArrayList.get(j).get().forEach((key, value) -> {
                    totalCharactersHashMap.merge(key, value, Integer::sum);
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n$---------- Total Characters ----------$");
        totalCharactersHashMap.forEach((key, value) -> System.out.println(key + " : " + value + " "));

        executorService.shutdown();
    }

    public static void PDFFilePath(){
        // PDF Document Path
        //pathArrayList.add("");
    }

}