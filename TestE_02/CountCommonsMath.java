import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

import java.util.ArrayList;

public class CountCommonsMath implements CountCommonsMathInterface {
    private double[] mathArray;

    CountCommonsMath(ArrayList<Integer> integerArrayList) {

        mathArray = new double[integerArrayList.size()];
        for (int i = 0; i < mathArray.length; i++) {
            mathArray[i] = integerArrayList.get(i);
        }
    }

    @Override
    public double countMean() {
        Mean mean = new Mean();
        return mean.evaluate(mathArray);
    }

    @Override
    public double countVariance() {
        Variance variance = new Variance();
        return variance.evaluate(mathArray);
    }

    @Override
    public double countSD() {
        StandardDeviation standardDeviation = new StandardDeviation();
        return standardDeviation.evaluate(mathArray);
    }

}