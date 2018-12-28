package TestExam_A172;
/*
    A172 Exam Question 2 (6 marks)
*/
public class Testing implements Runnable{

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + " is running ...");
        Thread t1 = new Thread(new Testing(), "STIW3054");
        Thread t2 = new Thread(new Testing());
        t2.start();
        t1.start();
        System.out.println(Thread.currentThread().getName() + " is running ....");
        t1.run();
        t2.run();
        System.out.println(t2.getName() + " is stopping ....");
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + " is running .....");
    }
}

/*
    ========== ========== ========== ========== ==========
    Thread[main,5,main] is running ...
    main is running ....
    main is running .....
    main is running .....
    Thread-0 is stopping ....
    Thread-0 is running .....
    STIW3054 is running .....
    ========== ========== ========== ========== ==========
 */
