import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class TestCyclicBarrier {

    public static void main(String[] args) {
        //how many threads are to wait at it before releasing them
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("All Thread passed Barrier"));

        Thread t1 = new Thread(new Task(cyclicBarrier));
        Thread t2 = new Thread(new Task(cyclicBarrier));
        Thread t3 = new Thread(new Task(cyclicBarrier));

        t1.start();
        t2.start();
        t3.start();
    }
}

class Task implements Runnable {
    private CyclicBarrier cyclicBarrier;

    Task(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " : reach the barrier");
            // waiting for all thread to reach
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " : pass the barrier once");
            // Timeout if thread does not come it will proceed
            cyclicBarrier.await(5, TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName() + " : pass the barrier twice");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
    ========== ========== ========== ========== ==========
    Thread-0 : reach the barrier
    Thread-2 : reach the barrier
    Thread-1 : reach the barrier
    All Thread passed Barrier
    Thread-1 : pass the barrier once
    Thread-0 : pass the barrier once
    Thread-2 : pass the barrier once
    All Thread passed Barrier
    Thread-2 : pass the barrier twice
    Thread-1 : pass the barrier twice
    Thread-0 : pass the barrier twice
    ========== ========== ========== ========== ==========
 */