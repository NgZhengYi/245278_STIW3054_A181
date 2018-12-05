import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threads/2);
        ArrayList<Future<Integer>> futureIntegerArrayList = new ArrayList<>();
        ArrayList<Future<HashMap<Character, Integer>>> futureHashMapArrayList = new ArrayList<>();

        // Place all the PDF document in a selected File
        ReadDocument readDocument = new ReadDocument("D:\\UUM A181\\STIW3054\\Test File");
        try {
            readDocument.readPDF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < readDocument.getArrayListPDF().size(); i++) {
            CallableCountWords callableCountWords = new CallableCountWords(readDocument.getArrayListPDF().get(i));
            Future<Integer> futureInteger = executorService.submit(callableCountWords);
            futureIntegerArrayList.add(futureInteger);

            CallableCountCharacters callableCountCharacters = new CallableCountCharacters(readDocument.getArrayListPDF().get(i));
            Future<HashMap<Character, Integer>> futureHashMap = executorService.submit(callableCountCharacters);
            futureHashMapArrayList.add(futureHashMap);
        }

        AtomicInteger totalWords = new AtomicInteger();
        System.out.println("\n$---------- Total Words for Each Document ----------$");
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

}