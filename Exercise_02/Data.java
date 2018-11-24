public class Data implements DataInterface {
    private int min, max;

    @Override
    public double[][] getSineData(double phase) {
        double[] xData = new double[100];
        double[] yData = new double[100];
        for (int i = 0; i < xData.length; i++) {
            double radians = phase + (1 * Math.PI / xData.length * i);
            xData[i] = radians;
            yData[i] = setRandomNumber(min, max);
        }
        return new double[][]{xData, yData};
    }

    @Override
    public int setRandomNumber(int min, int max) {
        this.min = min;
        this.max = max;
        int range = (max - min) + 1;
        return (int)(Math.random() * range + min);
    }

}
