public class Test_RT_01{

    public static void main(String[] args) {
        RunnableClass R1 = new RunnableClass("Thread 1");
        R1.start();

        RunnableClass R2 = new RunnableClass("Thread 2");
        R2.run();

        RunnableClass R3 = new RunnableClass("Thread 3");
        R3.start();
    }
}
