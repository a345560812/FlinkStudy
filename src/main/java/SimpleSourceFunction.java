import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import scala.Char;
import scala.concurrent.forkjoin.ThreadLocalRandom;

public class SimpleSourceFunction implements ParallelSourceFunction<Tuple2<String, Integer>> {

    private volatile boolean isRunning = true;


    public void run(SourceContext<Tuple2<String, Integer>> sourceContext) throws Exception {
        while (isRunning) {
            char c = (char) (int) (Math.random() * 26 + 65);
            sourceContext.collect(new Tuple2<String, Integer>(String.valueOf(c), 1));
        }
    }

    public void cancel() {
        isRunning = false;
    }
}
