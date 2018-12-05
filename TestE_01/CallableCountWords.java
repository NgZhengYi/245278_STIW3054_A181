import java.util.StringTokenizer;
import java.util.concurrent.Callable;

public class CallableCountWords implements Callable<Integer> {
    private String text;

    CallableCountWords(String text){
        this.text = text;
    }

    @Override
    public Integer call() throws Exception {
        StringTokenizer tokenizer = new StringTokenizer(text);
        Thread.sleep(1000);
        return tokenizer.countTokens();
    }

}
