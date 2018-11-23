public class MainClass {

    public static void main(String[] args) {

        Manager manager = new Manager();

        manager.setStrategy(new CalculateAddition());
        double addition = manager.operation(3, 2);
        System.out.println("Addition : " + addition);

        manager.setStrategy(new CalculateMultiply());
        double multiply = manager.operation(3, 2);
        System.out.println("Multiply : " + multiply);
    }
}
