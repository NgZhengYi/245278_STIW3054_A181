import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class MainClass {
    private static File[] pdfFile;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int processor = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processor);
        ArrayList<Future<ObjectPDF>> futureArrayList = new ArrayList<>();

        // File Directory
        checkDocument("D:\\UUM A181\\STIW3054\\Test File");

        for (int i = 0; i < pdfFile.length; i++) {
            TestPDF testPDF = new TestPDF(pdfFile[i].toString(), pdfFile[i].getName());
            Future<ObjectPDF> futureObjectPDF = executorService.submit(testPDF);
            futureArrayList.add(futureObjectPDF);
        }

        executorService.shutdown();
        /*
        System.out.println("$---------- File Detail List ----------$");
        for (Future<ObjectPDF> objectPDFFuture : futureArrayList) {
            System.out.println("File Name : " + objectPDFFuture.get().getFileName());
            System.out.println("Words : " + objectPDFFuture.get().getWordsNumber());
            System.out.println("Characters : " + objectPDFFuture.get().getCharacterHashMap());
            System.out.println();
        }
        */
        AtomicInteger totalWords = new AtomicInteger();
        futureArrayList.forEach(future -> {
            try {
                totalWords.addAndGet(future.get().getWordsNumber());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        HashMap<Character, Integer> totalCharectersHashMap = new HashMap<>();
        for (int i = 0; i < futureArrayList.size(); i++) {
            try {
                futureArrayList.get(i).get().getCharacterHashMap().forEach((key, value) -> {
                    totalCharectersHashMap.merge(key, value, Integer::sum);
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n$---------- Total Details ----------$");
        System.out.println("Number of Files : " + pdfFile.length);
        System.out.println("Total Words : " + totalWords);
        System.out.println("Total Characters : ");
        totalCharectersHashMap.forEach((key, value) -> System.out.print(key + ":" + value + " "));

        Mean mean = new Mean();
        Variance variance = new Variance();
        StandardDeviation standardDeviation = new StandardDeviation();
        double[] mathArray = new double[futureArrayList.size()];
        for (int j = 0; j < mathArray.length; j++) {
            mathArray[j] = futureArrayList.get(j).get().getWordsNumber();
        }

        System.out.println("\n\n$---------- Commons Math ----------$");
        System.out.printf("Mean : %.2f", mean.evaluate(mathArray));
        System.out.printf("%nVariance : %.4f", variance.evaluate(mathArray));
        System.out.printf("%nStandard Deviation : %.4f", standardDeviation.evaluate(mathArray));
    }

    private static void checkDocument(String path) {
        File directoryPath = new File(path);
        pdfFile = directoryPath.listFiles(pathname -> pathname.getName().endsWith(".pdf"));
        System.out.println("$---------- PDF File Found ----------$");
        Arrays.stream(pdfFile).map(File::getName).forEach(System.out::println);
    }
}
