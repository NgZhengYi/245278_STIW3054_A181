import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class Convert {
    private String path = "D://IntelliJ IDEA Projects/Exercise_01/Chess.xlsx";

    /**
     *  Read Data in a Excel File and Write it in Pdf File
     */
    public void convertExceltoPdf () throws IOException, DocumentException {
        // Read the Excel file in binary format into FileInputStream
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        // Read workbook into XSSFWorkbook
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
        // Read worksheet into XSSFSheet
        XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
        // To iterate over the rows
        Iterator<Row> rowIterator = xssfSheet.iterator();
        // create output PDF document page A4 size
        Document documentPDF = new Document(PageSize.A4);
        PdfWriter.getInstance(documentPDF, new FileOutputStream("Chess.pdf"));
        documentPDF.open();

        //PdfPTable pdfPTable = new PdfPTable(6);
        // Set Table Width Relative
        PdfPTable pdfPTable = new PdfPTable(new float[] {10, 45, 10, 10, 5, 20});
        pdfPTable.setWidthPercentage(100); // Default use 80%
        pdfPTable.getDefaultCell().setUseAscender(true);

        PdfPCell pdfPCell;

        Font font = FontFactory.getFont(FontFactory.TIMES, 12, Font.BOLD, BaseColor.DARK_GRAY);

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell>cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next(); //Fetch CELL

                switch(cell.getCellType()) { //Identify CELL type
                    // Handle String
                    case STRING:
                        pdfPCell = new PdfPCell(new Phrase(cell.getStringCellValue(), font));
                        //pdfPCell.setBackgroundColor(BaseColor.YELLOW);
                        pdfPTable.addCell(pdfPCell);
                        break;

                    // Handle Number
                    case NUMERIC:
                        pdfPCell = new PdfPCell(new Phrase(String.valueOf((int)cell.getNumericCellValue()), font));
                        //pdfPCell.setBackgroundColor(BaseColor.YELLOW);
                        pdfPTable.addCell(pdfPCell);
                        break;
                }
            }
        }
        // Add the table to PDF document
        documentPDF.add(pdfPTable);
        documentPDF.close();
        fileInputStream.close(); //close excel
        System.out.println("Successfully Converted to Pdf File");
    }
}
