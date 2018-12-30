package TestExam_A162;
/*
    A162 Exam Question 4 (5 marks)
 */
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Zoo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //ExecutorService es = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleWithFixedDelay(() -> {
            System.out.println("Open Zoo");
            //return null;
        }, 0, 1, TimeUnit.MINUTES);
        Future<?> result = es.submit(() -> System.out.println("Wake Staff"));
        System.out.println(result.get());
        es.shutdown();
    }
}

/*
    ========== ========== ========== ========== ==========
    Open Zoo
    Wake Staff
    null
    ========== ========== ========== ========== ==========
 */