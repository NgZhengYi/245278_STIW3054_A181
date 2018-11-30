import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadPDF readPDF = new ReadPDF();
        readPDF.extractText("D:/Downloads/Zhamri Paper.pdf");
        readPDF.calculateText();
    }
}
