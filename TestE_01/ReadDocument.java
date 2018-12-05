import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadDocument {
    private String path;
    private ArrayList<String> arrayListPDF = new ArrayList<>();

    ReadDocument(String path) {
        this.path = path;
    }

    public void readPDF() throws IOException {
        File directoryPath = new File(path);
        File[] pdfFile = directoryPath.listFiles(pathname -> pathname.getName().endsWith(".pdf"));
        System.out.println("$---------- Detected PDF File ----------$");
        Arrays.stream(pdfFile).map(File::getAbsolutePath).forEach(System.out::println);

        for (File file : pdfFile) {
            PDDocument document = PDDocument.load(file);
            PDFTextStripper textStripper = new PDFTextStripper();
            String text = textStripper.getText(document);
            arrayListPDF.add(text);
            document.close();
        }
        //arrayListPDF.forEach(System.out::println);
    }

    public ArrayList<String> getArrayListPDF() {
        return arrayListPDF;
    }

}
