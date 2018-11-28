import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

import java.util.ArrayList;

public class Number implements NumberInterface {
    private int min, max;
    private ArrayList<Integer> randomNumberArrayList = new ArrayList<Integer>();

    public int generateRandomNumber(int min, int max) {
        this.min = min;
        this.max = max;
        int range = (max - min) + 1;
        return (int)(Math.random() * range + min);
    }

    public void calculateSD(int count) throws Exception {
        Sum sum = new Sum();
        Mean mean = new Mean();
        Variance sVariance = new Variance();
        Variance pVariance = new Variance(false);
        StandardDeviation sampleSD = new StandardDeviation();
        StandardDeviation populationSD = new StandardDeviation(false);

        for (int i = 0; i < count; i++){
            int randomNumber = generateRandomNumber(min, max);
            randomNumberArrayList.add(randomNumber);

            double[] calculateArray = new double[randomNumberArrayList.size()];
            for (int j = 0; j < calculateArray.length; j++) {
                calculateArray[j] = randomNumberArrayList.get(j);
            }

            System.out.println("Count : " + (i+1));
            System.out.println("Random Number Added : " + randomNumber);
            System.out.println("Sum : " + sum.evaluate(calculateArray));
            System.out.println("Mean : " + mean.evaluate(calculateArray));
            System.out.println("Sample Variance : " + sVariance.evaluate(calculateArray));
            System.out.println("Population Variance : " + pVariance.evaluate(calculateArray));
            System.out.println("Sample SD : " + sampleSD.evaluate(calculateArray));
            System.out.println("Population SD : " + populationSD.evaluate(calculateArray) + "\n");

            Thread.sleep(1000);
        }
    }

}
