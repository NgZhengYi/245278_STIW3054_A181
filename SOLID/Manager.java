public class Manager implements Strategy {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public double operation(int num1, int num2) {
        return this.strategy.operation(num1, num2);
    }
}
