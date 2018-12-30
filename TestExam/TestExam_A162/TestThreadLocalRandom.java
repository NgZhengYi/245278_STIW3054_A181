package TestExam_A162;
/*
 * A162 Exam Section B Question 3 (15 marks)
 * Simple concurrent application using THREE threads that produce FOUR random number between 0 to 1000
 * Use ThreadLocalRandom class
 */
import java.util.concurrent.ThreadLocalRandom;

public class TestThreadLocalRandom {

    public static void main(String[] args) {
        RandomNumber randomNumber = new RandomNumber();

        //Thread t1 = new Thread(() -> randomNumber.printRandomNumber());
        Thread t1 = new Thread(randomNumber::printRandomNumber);
        Thread t2 = new Thread(randomNumber::printRandomNumber);
        Thread t3 = new Thread(randomNumber::printRandomNumber);

        t1.start();
        t2.start();
        t3.start();
    }
}

class RandomNumber {

    public void printRandomNumber() {

        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 4; i++) {
            System.out.println(threadName + " : " + ThreadLocalRandom.current().nextInt(0, 1000));
        }
    }
}

/*
    ========== ========== ========== ========== ==========
    Thread-2 : 626
    Thread-0 : 323
    Thread-1 : 543
    Thread-0 : 334
    Thread-2 : 70
    Thread-0 : 643
    Thread-0 : 884
    Thread-1 : 56
    Thread-2 : 486
    Thread-1 : 287
    Thread-2 : 564
    Thread-1 : 749
    ========== ========== ========== ========== ==========
 */