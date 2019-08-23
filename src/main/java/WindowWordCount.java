import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
public class WindowWordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Tuple2<String, Integer>> tuple2DataStreamSource = env.addSource(new SimpleSourceFunction());
        SingleOutputStreamOperator<Tuple2<String, Integer>> res = tuple2DataStreamSource.keyBy(0)
                .timeWindow(Time.seconds(10))
                .sum(1);
        res.print();
        env.execute("Window WordCount");
    }
}
