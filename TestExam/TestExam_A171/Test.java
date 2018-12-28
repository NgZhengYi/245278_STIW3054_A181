package TestExam_A171;
/*
    A171 Exam Question 5 (5 marks)
 */
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        CountSolution pt = new CountSolution();
        Thread t1 = new Thread(pt, "t1");
        Thread t2 = new Thread(pt, "t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("MyCount = " + CountSolution.mycount);
        System.out.println("Count = " + pt.getCount());
    }
}

class CountSolution implements Runnable {
    static int mycount;
    private AtomicInteger count = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 1; i <= 4; i++) {
            processSomething(i);
            i++;
            System.out.println(Thread.currentThread().getName() + " : " + count.incrementAndGet());
        }
    }

    public int getCount() {
        return this.count.get();
    }

    private void processSomething(int i) {
        try {
            Thread.sleep(i * 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
    ========== ========== ========== ========== ==========
    t1 : 1
    t2 : 2
    t2 : 4
    t1 : 3
    MyCount = 0
    Count = 4
    ========== ========== ========== ========== ==========
    // The thread number results are random
 */