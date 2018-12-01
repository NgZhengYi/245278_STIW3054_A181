import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallableFuture_1 {

    public static void main(String[] args) {

        Callable<Integer> integerCallable = () -> {
            int result = new Random().nextInt(10) + 1;
            return result;
        };

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        int result = 0;
        for (int i = 0; i < 5; i++){
            Future<Integer> integerFuture = executorService.submit(integerCallable);
            try {
                System.out.println("Random Number " + (i+1) + " : " + integerFuture.get());
                result = integerFuture.get() + result;
                Thread.sleep(1000);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Total Results : " + result);
        executorService.shutdown();
    }
}
