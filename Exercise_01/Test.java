import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException, DocumentException {
        WebScrapping webScrapping = new WebScrapping();
        Excel excel = new Excel();
        Convert convert = new Convert();

        webScrapping.extractTable();
        webScrapping.printArrayRecord();
        excel.writeExcel(webScrapping.getArrayList());
        convert.convertExceltoPdf();

    }
}
