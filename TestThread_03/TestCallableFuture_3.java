import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCallableFuture_3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ArrayList<Future<Integer>> futureIntegerArrayList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Future<Integer> futureInteger = executorService.submit(new CallableInterface());
            futureIntegerArrayList.add(futureInteger);
        }

        AtomicInteger totalNumbers = new AtomicInteger();
        futureIntegerArrayList.forEach(future -> {
            try {
                System.out.println("Random Number : " + future.get());
                totalNumbers.addAndGet(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Total Random Numbers : " + totalNumbers);
        executorService.shutdown();
    }
}

class CallableInterface implements Callable <Integer>{

    @Override
    public Integer call() throws Exception {
        Thread.sleep(2000);
        return new Random().nextInt(100) + 1;
    }
}
