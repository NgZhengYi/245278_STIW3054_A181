import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallableFuture_2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ArrayList<Future<Integer>> futureArrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            Future<Integer> futureInteger = executorService.submit(new CallableClass());
            futureArrayList.add(futureInteger);
        }

        futureArrayList.forEach(integerFuture -> {
            try {
                System.out.println("Random Number : " + integerFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdown();
    }

}

class CallableClass implements Callable <Integer>{

    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return new Random().nextInt(10) + 1;
    }
}