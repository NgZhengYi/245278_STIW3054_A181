import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

public class CallableCountWords implements Callable<Integer> {
    private String path;

    CallableCountWords(String path){
        this.path = path;
    }

    @Override
    public Integer call() throws Exception {
        File file = new File(path);
        PDDocument document = PDDocument.load(file);
        PDFTextStripper textStripper = new PDFTextStripper();

        String text = textStripper.getText(document);
        StringTokenizer tokenizer = new StringTokenizer(text);

        document.close();
        Thread.sleep(1000);
        return tokenizer.countTokens();
    }
}
