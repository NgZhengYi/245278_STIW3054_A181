package TestExam_A172;
/*
 * A172 Exam Case Study Question 3 (15 marks)
 * Write a java program by extending Thread class to display "STIW3054 RT Programming"
 * The thread should be running until an interrupt() method is called
 */

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadInterrupt extends Thread {

    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger();
        Thread t1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                if (count.intValue() >= 10) {
                    Thread.currentThread().interrupt();
                    System.out.println("Thread Interrupt !!!");
                } else {
                    count.incrementAndGet();
                    System.out.println(Thread.currentThread().getName() + " STIW3054 RT Programming");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t1.start();
    }

}

/*
    ========== ========== ========== ========== ==========
    Thread-0 STIW3054 RT Programming
    Thread-0 STIW3054 RT Programming
    Thread-0 STIW3054 RT Programming
    Thread-0 STIW3054 RT Programming
    Thread-0 STIW3054 RT Programming
    Thread-0 STIW3054 RT Programming
    Thread-0 STIW3054 RT Programming
    Thread-0 STIW3054 RT Programming
    Thread-0 STIW3054 RT Programming
    Thread-0 STIW3054 RT Programming
    Thread Interrupt !!!
    ========== ========== ========== ========== ==========
 */