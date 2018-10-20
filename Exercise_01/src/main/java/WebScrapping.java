import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class WebScrapping {
    private final ArrayList<Chess> chessArrayList = new ArrayList<Chess>();

    /**
     *  Extract HTML table
     */
    public void extractTable(){
        String link = "http://chess-results.com/tnr380806.aspx?lan=1&zeilen=99999";

        try {
            Document document = Jsoup.connect(link).userAgent("Chrome/69.0.3497.100").get();
            String title = document.title();
            System.out.println("Page Title: " + title);

            Elements elements = document.getElementsByClass("CRs1");
            Elements tableRows = elements.select("tr");

            for (int i = 1; i < tableRows.size(); i++) {
                String test_name = tableRows.get(i).select("td:eq(2)").text();
                String test_fidelID = tableRows.get(i).select("td:eq(3)").text();
                String test_FED = tableRows.get(i).select("td:eq(4)").text();
                String test_Rtg = tableRows.get(i).select("td:eq(5)").text();
                String test_Club = tableRows.get(i).select("td:eq(6)").text();

                chessArrayList.add(new Chess(test_name, test_fidelID, test_FED, test_Rtg, test_Club));
                //System.out.printf("%-3s %-60s %-6s %-5s %-2s %-25s %n",i,test_name,test_fieldID,
                //        test_FED,test_Rtg,test_Club);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  Display the Record stored in ArrayList
     */
    public void printArrayRecord() {
        int i = 1;
        System.out.println("--------------- Table ---------------");
        for (Chess chess : chessArrayList) {
            System.out.printf("%-3s %-60s %-6s %-5s %-2s %-25s %n",i,chess.getName(),chess.getFidelID(),
                    chess.getFED(),chess.getRtg(),chess.getClub());
            i++;
        }
    }

    /**
     *  Access the ArrayList
     */
    public ArrayList<Chess> getArrayList() {
        return chessArrayList;
    }
}
