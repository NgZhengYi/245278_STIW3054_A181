import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Manager implements ChartInterface, DataInterface {
    private ChartInterface chartInterface;
    private DataInterface dataInterface;

    public void setChartInterface(ChartInterface chartInterface) {
        this.chartInterface = chartInterface;
    }

    public void setDataInterface(DataInterface dataInterface) {
        this.dataInterface = dataInterface;
    }

    @Override
    public XYChart getXYChart(double[][] initdata) {
        return this.chartInterface.getXYChart(initdata);
    }

    @Override
    public SwingWrapper getSwingWrapper() {
        return this.chartInterface.getSwingWrapper();
    }

    @Override
    public void getUpdateResult(double[][] initdata) {
        this.chartInterface.getUpdateResult(initdata);
    }

    @Override
    public double[][] getSineData(double phase) {
        return this.dataInterface.getSineData(phase);
    }

    @Override
    public int setRandomNumber(int min, int max) {
        return this.dataInterface.setRandomNumber(min, max);
    }
}
