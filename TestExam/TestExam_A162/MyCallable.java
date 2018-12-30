package TestExam_A162;
/*
    A162 Exam Question 6 (10 marks)
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyCallable implements Callable<String> {

    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> list = new ArrayList<>();
        Callable<String> callable = new MyCallable();
        for (int i = 0; i < 4; i++) {
            Future<String> future = executorService.submit(callable);
            list.add(future);
        }
        for (Future<String> fut : list) {

            try {
                System.out.println(fut.get());
                System.out.println("---> Test Callable 1");
                System.out.println("---> Test Callable 2");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}

/*
    ========== ========== ========== ========== ==========
    pool-1-thread-1
    ---> Test Callable 1
    ---> Test Callable 2
    pool-1-thread-2
    ---> Test Callable 1
    ---> Test Callable 2
    pool-1-thread-1
    ---> Test Callable 1
    ---> Test Callable 2
    pool-1-thread-2
    ---> Test Callable 1
    ---> Test Callable 2
    ========== ========== ========== ========== ==========
 */