package reducers;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

public class CountReducer extends Reducer<Text,IntWritable,NullWritable,IntWritable>{

	public void Reduce(Text key,Iterable<IntWritable> records,Context context) 
					throws IOException, InterruptedException{
		int sum = 0;
		
		for(IntWritable record:records){
			sum += record.get();
		}
		
		context.write(NullWritable.get(), new IntWritable(sum));
	}
}
