package TestExam_A172;
/*
    A172 Exam Section B Question 2 (15 marks)
 */
public class MultiplicationTable {

    public static void main(String[] args) {
        Calculate calculate = new Calculate();

        Thread t1 = new Thread(() -> calculate.printNumber(2));
        Thread t2 = new Thread(() -> calculate.printNumber(3));
        Thread t3 = new Thread(() -> calculate.printNumber(4));

        t1.start();
        t2.start();
        t3.start();
    }
}

class Calculate {
    void printNumber(int value) {
        synchronized (this) {
            String threadName = Thread.currentThread().getName();
            for (int i = 2; i <= 4; i++) {
                System.out.println(threadName + ": " + value + " * " + i + " = " + (value * i));
            }
        }
    }
}


/*
    ========== ========== ========== ========== ==========
    Thread-0: 2 * 2 = 4
    Thread-0: 2 * 3 = 6
    Thread-0: 2 * 4 = 8
    Thread-2: 4 * 2 = 8
    Thread-2: 4 * 3 = 12
    Thread-2: 4 * 4 = 16
    Thread-1: 3 * 2 = 6
    Thread-1: 3 * 3 = 9
    Thread-1: 3 * 4 = 12
    ========== ========== ========== ========== ==========
 */