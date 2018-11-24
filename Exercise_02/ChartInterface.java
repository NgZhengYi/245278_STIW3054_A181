import org.knowm.xchart.SwingWrapper;
        import org.knowm.xchart.XYChart;

public interface ChartInterface {

    public XYChart getXYChart(double[][] initdata);
    public SwingWrapper getSwingWrapper();
    public void getUpdateResult(double [][] initdata);
}
