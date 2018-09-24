public class RunnableClass implements Runnable{
    private Thread T;
    private String Tname;

    RunnableClass (String Tname){
        this.Tname = Tname;
        System.out.println("---------- Creating " + Tname + " ----------");
    }

    @Override
    public void run(){
        System.out.println("Running " + Tname + " ......");

        try {
            for (int i = 0; i < 3; i++){
                System.out.println("Processing " + Tname + " ......");
                Thread.sleep(2000);
            }
        } catch (InterruptedException e){
            System.out.println(Tname + " Interrupted !!");
        }
        System.out.println("---------- Completed " + Tname + " ----------");
    }

    public void start(){
        System.out.println("Starting " + Tname + " ......");
        if (T == null){
            T = new Thread(this, Tname);
            T.start();
        }
    }
}
