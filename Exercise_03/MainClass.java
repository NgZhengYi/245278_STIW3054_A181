public class MainClass {

    public static void main(String[] args) throws Exception{
        Manager manager = new Manager();

        manager.setNumberInterface(new Number());

        manager.generateRandomNumber(100, 900);
        manager.calculateSD(10);
    }
}
