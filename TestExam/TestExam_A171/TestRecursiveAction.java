package TestExam_A171;
/*
 * A171 Exam Case Study Question 4 (20 marks)
 * You are required to develop a simple program to simulate Fork and Join using RecursiveAction
 */

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class TestRecursiveAction {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new DemoTask(0, 20));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }
}

class DemoTask extends RecursiveAction {
    private int start, end;
    private static final int MAX = 10;

    DemoTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if ((end - start) < MAX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
            }
        } else {
            int middle = (start + end) / 2;
            DemoTask left = new DemoTask(start, middle);
            DemoTask right = new DemoTask(middle, end);
            left.fork();
            right.fork();
        }
    }
}

/*
    ========== ========== ========== ========== ==========
    ForkJoinPool-1-worker-1 : 15
    ForkJoinPool-1-worker-2 : 5
    ForkJoinPool-1-worker-3 : 10
    ForkJoinPool-1-worker-3 : 11
    ForkJoinPool-1-worker-3 : 12
    ForkJoinPool-1-worker-3 : 13
    ForkJoinPool-1-worker-3 : 14
    ForkJoinPool-1-worker-2 : 6
    ForkJoinPool-1-worker-5 : 0
    ForkJoinPool-1-worker-1 : 16
    ForkJoinPool-1-worker-5 : 1
    ForkJoinPool-1-worker-2 : 7
    ForkJoinPool-1-worker-2 : 8
    ForkJoinPool-1-worker-2 : 9
    ForkJoinPool-1-worker-5 : 2
    ForkJoinPool-1-worker-5 : 3
    ForkJoinPool-1-worker-5 : 4
    ForkJoinPool-1-worker-1 : 17
    ForkJoinPool-1-worker-1 : 18
    ForkJoinPool-1-worker-1 : 19
    ========== ========== ========== ========== ==========
 */