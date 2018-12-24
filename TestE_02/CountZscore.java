import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CountZscore implements CountZscoreInterface {
    private double mean, standardDeviation;
    private ArrayList<Integer> charactersLengthArrayList;

    CountZscore(double mean, double standardDeviation, ArrayList<Integer> charactersLengthArrayList) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.charactersLengthArrayList = charactersLengthArrayList;
    }

    @Override
    public ArrayList<Double> countZscore() {
        ArrayList<Double> zScoreArrayList;
        DecimalFormat decimalFormat = new DecimalFormat(".##");

        zScoreArrayList = charactersLengthArrayList.stream().mapToDouble(integer ->
                (integer - mean) / standardDeviation).mapToObj(zScore ->
                    Double.valueOf(decimalFormat.format(zScore))).collect(Collectors.toCollection(ArrayList::new));
        return zScoreArrayList;
    }
}
