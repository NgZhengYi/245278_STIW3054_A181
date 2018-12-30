package TestExam_A162;
/*
    A162 Exam Question 5 (5 marks)
 */
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    // Initial value 15
    static AtomicInteger ai = new AtomicInteger(15);

    public static void check() {
        assert (ai.intValue() % 2 == 0);
        System.out.println("Check : " + ai);
    }

    public static void increment() {
        ai.incrementAndGet();
        System.out.println("Increment : " + ai);
    }

    public static void decrement() {
        ai.decrementAndGet();
        System.out.println("Decrement : " + ai);
    }

    public static void compare() {
        ai.compareAndSet(10, 11);
        System.out.println("Compare : " + ai);
    }

    public static void main(String[] args) {
        increment();
        decrement();
        compare();
        check();
        System.out.println("Finally : " + ai);
    }
}

/*
    ========== ========== ========== ========== ==========
    Increment : 16
    Decrement : 15
    Compare : 15
    Check : 15
    Finally : 15
    ========== ========== ========== ========== ==========
 */