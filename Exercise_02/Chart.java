import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class Chart implements ChartInterface {
    XYChart xyChart = null;
    SwingWrapper<XYChart> swingWrapper = null;

    // Create Chart
    @Override
    public XYChart getXYChart(double[][] initdata) {
        xyChart = QuickChart.getChart("Real-Time Random Number", "Count",
                "Number", "Value", initdata[0], initdata[1]);
        xyChart.getStyler().setYAxisMax(100.0);
        xyChart.getStyler().setYAxisMin(0.0);
        xyChart.getStyler().setToolTipsEnabled(true);
        return xyChart;
    }

    // Display it
    @Override
    public SwingWrapper<XYChart> getSwingWrapper() {
        swingWrapper = new SwingWrapper<XYChart>(xyChart);
        swingWrapper.displayChart();
        return swingWrapper;
    }

    @Override
    public void getUpdateResult(double[][] initdata) {
        xyChart.updateXYSeries("Value", initdata[0], initdata[1], null);
        swingWrapper.repaintChart();
    }

}