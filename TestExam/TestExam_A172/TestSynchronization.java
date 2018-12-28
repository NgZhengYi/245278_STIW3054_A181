package TestExam_A172;
/*
    A172 Exam Question 5 (5 marks)
 */
public class TestSynchronization {

    public static void main(String[] args) {
        Table obj = new Table();
        MyThread1 t1 = new MyThread1(obj);
        MyThread2 t2 = new MyThread2(obj);
        t1.start();
        t2.start();
    }
}

class Table {
    synchronized void printTable(int n) {
        for (int i = 1; i < 5; i++) {
            System.out.println(n * i);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyThread1 extends Thread {
    private Table t;

    MyThread1(Table t) {
        this.t = t;
    }

    @Override
    public void run() {
        t.printTable(7);
    }
}

class MyThread2 extends Thread {
    private Table t;

    MyThread2(Table t) {
        this.t = t;
    }

    @Override
    public void run() {
        t.printTable(115);
    }
}

/*
    ========== ========== ========== ========== ==========
    7
    14
    21
    28
    115
    230
    345
    460
    ========== ========== ========== ========== ==========
 */