public class Manager implements NumberInterface {
    private NumberInterface numberInterface;

    public void setNumberInterface(NumberInterface numberInterface) {
        this.numberInterface = numberInterface;
    }

    public int generateRandomNumber(int min, int max) {
        return this.numberInterface.generateRandomNumber(min, max);
    }

    public void calculateSD(int count) throws Exception{
        this.numberInterface.calculateSD(count);
    }
}
