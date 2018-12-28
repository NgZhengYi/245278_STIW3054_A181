package TestExam_A172;
/*
    A172 Exam Question 6 (10 marks)
 */
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("The installation failed ...");
            }
        });

        for (int i = 0; i < 3; ++i) {
            executorService.execute(new Exam(i + 1));
        }
        System.out.println("The installation succeed ...");
        executorService.shutdown();
    }
}

class Exam implements Runnable {
    private int id;
    private static Lock lock = new ReentrantLock();

    Exam(int id) {
        this.id = id;
        lock.lock();
        id++;
        lock.unlock();
    }

    @Override
    public void run() {
        System.out.println(id + " starts from Ionic ...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread ID: " + id + " is finished ...");

        try {
            System.out.println("After Cordova ...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
    ========== ========== ========== ========== ==========
    The installation succeed ...
    2 starts from Ionic ...
    1 starts from Ionic ...
    3 starts from Ionic ...
    Thread ID: 3 is finished ...
    After Cordova ...
    Thread ID: 2 is finished ...
    After Cordova ...
    Thread ID: 1 is finished ...
    After Cordova ...
    ========== ========== ========== ========== ==========
 */