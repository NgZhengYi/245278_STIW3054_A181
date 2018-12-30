package TestExam_A162;
/*
    A162 Exam Question 2 (6 marks)
 */
public class IntroExample extends Thread {

    public static void main(String[] args) {
        IntroExample t1 = new IntroExample();
        IntroExample t2 = new IntroExample();
        System.out.println("Name of t1 : " + t1.getName());
        System.out.println("Name of t2 : " + t2.getName());
        System.out.println("ID of t1 : " + t1.getId());

        t1.start();
        t2.start();

        t1.setName("STIW3054");
        System.out.println("After changing name of t1 : " + t1.getName());
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running ....");
    }
}

/*
    ========== ========== ========== ========== ==========
    Name of t1 : Thread-0
    Name of t2 : Thread-1
    ID of t1 : 12
    After changing name of t1 : STIW3054
    STIW3054 running ....
    Thread-1 running ....
    ========== ========== ========== ========== ==========
 */