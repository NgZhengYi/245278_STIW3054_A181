import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class GraphNormalization implements GraphNormalizationInterface {
    private ArrayList<Double> zScoreArrayList;
    private double[] copyData;
    private XYChart xyChart = null;
    private SwingWrapper<XYChart> swingWrapper = null;

    GraphNormalization(ArrayList<Double> zScoreArrayList) {
        this.zScoreArrayList = zScoreArrayList;
    }

    @Override
    public void normalizationGraph() throws InterruptedException {
        createChart();
        createSwingWrapper();
        updateData();
    }

    private void createChart() {
        xyChart = new XYChartBuilder().width(1280).height(720).title("Normalization Graph").xAxisTitle("Word Count")
                .yAxisTitle("Z-Value").build();
        xyChart.addSeries("Value", null, zScoreArrayList);
        xyChart.getStyler().setXAxisMin(0.0);
        xyChart.getStyler().setYAxisMax(4.0);
        xyChart.getStyler().setYAxisMin(-4.0);
        xyChart.getStyler().setXAxisDecimalPattern("#####");
        xyChart.getStyler().setYAxisDecimalPattern("#.#");
        xyChart.getStyler().setToolTipsEnabled(true);
        xyChart.getStyler().setSeriesColors(new Color[]{Color.RED});
    }

    private void createSwingWrapper() {
        swingWrapper = new SwingWrapper<>(xyChart);
        swingWrapper.displayChart();
    }

    private void updateData() throws InterruptedException {
        copyData = new double[20];

        for (int i = 0; i < zScoreArrayList.size() - 20; i++) {
            IntStream.range(0, 20).forEach(j -> copyData[j] = zScoreArrayList.get(j));
            xyChart.updateXYSeries("Value", null, copyData, null);
            swingWrapper.repaintChart();
            zScoreArrayList.remove(0);
            Thread.sleep(1000);
        }
    }

}