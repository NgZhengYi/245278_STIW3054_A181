package TestExam_A171;
/*
 * A171 Exam Section B Question 1 (10 marks)
 * Write a program to calculate day between certain period using concurrent.TimeUnit
 */
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class TestTimeUnit {

    public static void main(String[] args) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        // year, month, date
        c1.set(2018, 12, 29);
        c2.set(2018, 12, 31);

        TimeUnit timeUnit = TimeUnit.DAYS;
        long results = timeUnit.convert(c2.getTimeInMillis() - c1.getTimeInMillis(), TimeUnit.MILLISECONDS);
        System.out.println("Different : " + results + " days");
    }
}

/*
    ========== ========== ========== ========== ==========
    Different : 2 days
    ========== ========== ========== ========== ==========
 */