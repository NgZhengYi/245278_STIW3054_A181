public class MyThread extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 1; i <= 2; i++){
                System.out.println("Running..." + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){

        }
        System.out.println("Complete...");
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        System.out.println("Name of t1 : " + t1.getName());
        System.out.println("Name of t2 : " + t2.getName());
        System.out.println("Id of t1 : " + t1.getId());

        t1.start();
        t2.start();

        System.out.println("Starting...");
        t1.setName("STIW3054");
        System.out.println("After changing name of t1 : " + t1.getName());
    }
}
