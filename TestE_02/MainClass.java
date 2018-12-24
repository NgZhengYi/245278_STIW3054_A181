import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MainClass {

    public static void main(String[] args) throws Exception {
        int processor = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processor);
        ArrayList<Future<ObjectPDF>> futureArrayList;
        File[] pdfFile;

        // File Directory
        File directoryPath = new File("D:\\UUM A181\\STIW3054\\Test File");
        pdfFile = directoryPath.listFiles(pathname -> pathname.getName().endsWith(".pdf"));
        System.out.println("$---------- PDF File Found ----------$");
        if (pdfFile != null) {
            Arrays.stream(pdfFile).map(File::getName).forEach(System.out::println);
        } else {
            System.out.println("No PDF File Detected");
            return;
        }
        // Start Reading PDF using Executor
        futureArrayList = Arrays.stream(pdfFile).map(file ->
                new ProcessPDF(file.toString(), file.getName())).map(executorService::submit)
                .collect(Collectors.toCollection(ArrayList::new));

        executorService.shutdown();
        /*
        System.out.println("$---------- Each File Detail ----------$");

        for (Future<ObjectPDF> objectPDFFuture : futureArrayList) {
            System.out.println("File Name : " + objectPDFFuture.get().getFileName());
            System.out.println("Words : " + objectPDFFuture.get().getWordsNumber());
            System.out.println("Characters : " + objectPDFFuture.get().getCharactersNumber());
            System.out.println("List : " + objectPDFFuture.get().getCharacterHashMap());
            System.out.println();
        }
        */
        CountTotalPDF countTotalPDF = new CountTotalPDF(futureArrayList);
        System.out.println("\n$---------- Total Detail ----------$");
        System.out.println("Number of Files : " + pdfFile.length);
        System.out.println("Total Words : " + countTotalPDF.calculateTotalWords());
        System.out.println("ArrayList of Total Words Length : " + countTotalPDF.countCharactersLengthList().size());
        System.out.println("Total Characters : " + countTotalPDF.calculateTotalCharacters());
        countTotalPDF.charactersHashMap().forEach((key, value) -> System.out.print(key + ":" + value + " "));

        ArrayList<Integer> charactersLengthArrayList = countTotalPDF.countCharactersLengthList();
        CountCommonsMath countCommonsMath = new CountCommonsMath(charactersLengthArrayList);
        double mean = countCommonsMath.countMean();
        double variance = countCommonsMath.countVariance();
        double standardDeviation = countCommonsMath.countSD();

        System.out.println("\n\n$---------- Commons Math ----------$");
        System.out.printf("Mean : %.2f", mean);
        System.out.printf("%nVariance : %.4f", variance);
        System.out.printf("%nStandard Deviation : %.4f", standardDeviation);

        CountZscore countZscore = new CountZscore(mean, standardDeviation, charactersLengthArrayList);
        ArrayList<Double> zScoreArrayList = countZscore.countZscore();
        //zScoreArrayList.forEach(aDouble -> System.out.println("Z-score : " + aDouble));

        ExecutorService es = Executors.newFixedThreadPool(2);
        GraphNormalization graphNormalization = new GraphNormalization(zScoreArrayList);
        Thread t1 = new Thread(() -> {
            try {
                graphNormalization.normalizationGraph();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        DrawBoxplot drawBoxplot = new DrawBoxplot(zScoreArrayList);
        Thread t2 = new Thread(drawBoxplot::boxplotGraph);

        es.execute(t1);
        es.execute(t2);
        es.shutdown();

    }

}