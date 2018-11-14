public class TestNumber {

    public void printEvenNumber() throws InterruptedException {
        synchronized (this) {
            System.out.println("Running ...... " + Thread.currentThread().getName());
            for (int i = 0; i <= 100; i++){
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                }

                if (i == 20) {
                    System.out.println("Waiting ...... " + Thread.currentThread().getName());
                    wait();
                    System.out.println("Resuming ...... " + Thread.currentThread().getName());
                }
            }
            System.out.println("Complete ......" + Thread.currentThread().getName());
        }
    }

    public void printOddNumber() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Running ...... " + Thread.currentThread().getName());
            for (int i = 0; i < 100; i++) {
                if (i % 2 != 0) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                }
            }
            System.out.println("Complete ......" + Thread.currentThread().getName());
            notifyAll();
        }
    }

}
