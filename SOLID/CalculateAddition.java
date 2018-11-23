public class CalculateAddition implements Strategy {

    @Override
    public double operation(int num1, int num2) {
        return num1 + num2;
    }
}
