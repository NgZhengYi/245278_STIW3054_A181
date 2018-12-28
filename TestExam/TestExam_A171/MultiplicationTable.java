package TestExam_A171;
/*
    A171 Exam Section B Question 2 (15 marks)
 */
public class MultiplicationTable {

    public static void main(String[] args) {
        Calculate calculate = new Calculate();

        Thread t1 = new Thread(() -> calculate.printNumber(1));
        Thread t2 = new Thread(() -> calculate.printNumber(2));
        Thread t3 = new Thread(() -> calculate.printNumber(3));

        t1.start();
        t2.start();
        t3.start();
    }
}

class Calculate {

    void printNumber(int value) {
        String threadName = Thread.currentThread().getName();
        for (int i = 1; i < 4; i++) {
            System.out.println(threadName + ": " + value + " * " + i + " = " + (value*i));
        }
    }
}

/*
    ========== ========== ========== ========== ==========
    Thread-0: 1 * 1 = 1
    Thread-2: 3 * 1 = 3
    Thread-2: 3 * 2 = 6
    Thread-0: 1 * 2 = 2
    Thread-1: 2 * 1 = 2
    Thread-0: 1 * 3 = 3
    Thread-2: 3 * 3 = 9
    Thread-1: 2 * 2 = 4
    Thread-1: 2 * 3 = 6
    ========== ========== ========== ========== ==========
 */