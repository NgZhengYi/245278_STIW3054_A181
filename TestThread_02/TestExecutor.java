import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestExecutor {

    public static void main(String[] args) {

        TestNumber testNumber = new TestNumber();
        ExecutorService executorService = Executors.newCachedThreadPool();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testNumber.printEvenNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testNumber.printOddNumber();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(t1);
        executorService.execute(t2);

        executorService.shutdown();
    }
}
