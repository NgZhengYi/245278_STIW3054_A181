package TestExam_A171;
/*
    A171 Exam Question 6 (10 marks)
 */
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCyclicBarriers {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("We will pass this exam ...");
            }
        });

        for (int i = 0; i < 3; ++i) {
            executorService.execute(new Exam(i + 1, barrier));
        }
        executorService.shutdown();
    }
}

class Exam implements Runnable {
    private int id;
    private CyclicBarrier cyclicBarrier;

    Exam(int id, CyclicBarrier cyclicBarrier) {
        this.id = id;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        System.out.println(id + " Starts the exam ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread ID: " + id + " finishing ...");

        try {
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " After study ...");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

/*
    ========== ========== ========== ========== ==========
    2 Starts the exam ...
    3 Starts the exam ...
    1 Starts the exam ...
    Thread ID: 2 finishing ...
    Thread ID: 3 finishing ...
    Thread ID: 1 finishing ...
    We will pass this exam ...
    pool-1-thread-1 After study ...
    pool-1-thread-2 After study ...
    pool-1-thread-3 After study ...
    ========== ========== ========== ========== ==========
 */