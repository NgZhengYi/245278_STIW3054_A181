package TestExam_A171;
/*
    A171 Exam Question 4 (5 marks)
 */
public class CheckProcessor {

    public static void main(String[] args) {
        int x = Runtime.getRuntime().availableProcessors();
        //int y = Runtime.getRuntime().freeMemory();
        long y = Runtime.getRuntime().freeMemory();
        //int z = Runtime.getRuntime().totalMemory();
        long z = Runtime.getRuntime().totalMemory();

        System.out.println("Processor : " + x);
        System.out.println("Free Memory : " + y);
        System.out.println("Total Memory : " + z);
    }
}

/*
    Free Memory and Total Memory required variable type long
 */