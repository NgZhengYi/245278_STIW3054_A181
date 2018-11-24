public class MainClass {

    public static void main(String[] args) throws Exception {
        double phase = 0.0;

        Manager manager = new Manager();

        manager.setChartInterface(new Chart());
        manager.setDataInterface(new Data());

        manager.setRandomNumber(0, 100);
        manager.getXYChart(manager.getSineData(phase));
        manager.getSwingWrapper();

        while (phase <= 1000000) {
            manager.getUpdateResult(manager.getSineData(phase++));
            Thread.sleep(500);
        }
    }
}