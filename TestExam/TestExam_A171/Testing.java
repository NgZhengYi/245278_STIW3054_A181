package TestExam_A171;
/*
    A171 Exam Question 2 (6 marks)
*/
public class Testing implements Runnable{

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + " is running ...");
        Thread t1 = new Thread(new Testing(), "t1");
        Thread t2 = new Thread(new Testing());
        t1.start();
        t2.start();
        System.out.println(Thread.currentThread().getName() + " is running ....");
        t1.start();
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
    t1 is running .....
    Exception in thread "main" java.lang.IllegalThreadStateException
    ========== ========== ========== ========== ==========
    // Thread cannot be started twice
 */