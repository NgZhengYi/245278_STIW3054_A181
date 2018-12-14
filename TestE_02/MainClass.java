import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainClass {
    private static File[] pdfFile;

    public static void main(String[] args) {
        int processor = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processor);
        ArrayList<Future<ObjectPDF>> futureArrayList = new ArrayList<>();

        // File Directory
        File directoryPath = new File("D:\\UUM A181\\STIW3054\\Test File");
        pdfFile = directoryPath.listFiles(pathname -> pathname.getName().endsWith(".pdf"));
        System.out.println("$---------- PDF File Found ----------$");
        Arrays.stream(pdfFile).map(File::getName).forEach(System.out::println);

        for (int i = 0; i < pdfFile.length; i++) {
            TestPDF testPDF = new TestPDF(pdfFile[i].toString(), pdfFile[i].getName());
            Future<ObjectPDF> futureObjectPDF = executorService.submit(testPDF);
            futureArrayList.add(futureObjectPDF);
        }

        executorService.shutdown();
        /*
        System.out.println("$---------- File Detail List ----------$");
        try {
            for (Future<ObjectPDF> objectPDFFuture : futureArrayList) {
                System.out.println("File Name : " + objectPDFFuture.get().getFileName());
                System.out.println("Words : " + objectPDFFuture.get().getWordsNumber());
                System.out.println("Characters : " + objectPDFFuture.get().getCharactersNumber());
                System.out.println("List : " + objectPDFFuture.get().getCharacterHashMap());
                System.out.println();
            }
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        */
        CountWords countWords = new CountWords(futureArrayList);
        CountCharacters countCharacters = new CountCharacters(futureArrayList);
        CharactersHashMap charactersHashMap = new CharactersHashMap(futureArrayList);
        CountCommonsMath countCommonsMath = new CountCommonsMath(futureArrayList);

        System.out.println("\n$---------- Total Details ----------$");
        System.out.println("Number of Files : " + pdfFile.length);
        System.out.println("Total Words : " + countWords.calculateTotalWords());
        System.out.println("Total Characters : " + countCharacters.calculateTotalCharacters());
        charactersHashMap.charactersHashMap().forEach((key, value) -> System.out.print(key + ":" + value + " "));

        System.out.println("\n\n$---------- Commons Math ----------$");
        System.out.printf("Mean : %.2f", countCommonsMath.countMean());
        System.out.printf("%nVariance : %.4f", countCommonsMath.countVariance());
        System.out.printf("%nStandard Deviation : %.4f", countCommonsMath.countSD());
        System.out.printf("%nNormal Distribution : %.4f", countCommonsMath.countNormalDistribution(countWords.calculateTotalWords().intValue()));
    }

}
