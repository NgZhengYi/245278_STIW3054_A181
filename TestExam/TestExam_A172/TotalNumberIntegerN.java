package TestExam_A172;
/*
 * A172 Exam Case Study Question 4 (20 marks)
 * Write a Java program by implementing Callable<Integer> interface to count the
 * total number of integer N starting from 1. Then, display the result using
 * Future<Integer> and ExecuterService interfaces.
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TotalNumberIntegerN {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executerService = Executors.newSingleThreadExecutor();
        CallableClass c1 = new CallableClass(10);

        Future<Integer> futureInteger = executerService.submit(c1);
        Integer result = futureInteger.get();
        System.out.println(result);

        executerService.shutdown();
    }
}

class CallableClass implements Callable<Integer> {
    private int num = 0;

    CallableClass(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        int result = 0;
        for (int i = 1; i <= num; i++) {
            result += i;
        }
        return result;
    }
}

/*
    ========== ========== ========== ========== ==========
    55
    ========== ========== ========== ========== ==========
 */