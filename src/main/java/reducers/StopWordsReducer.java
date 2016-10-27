package reducers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StopWordsReducer extends Reducer<Text,IntWritable,NullWritable,Text>{

	@Override
	public void reduce(Text key,Iterable<IntWritable> records,Context context) 
					throws IOException, InterruptedException{
		int sum = 0;
		
		for(IntWritable record:records){
			sum += record.get();
		}
		
		context.write(NullWritable.get(), new Text(key+","+String.valueOf(sum)));
	}
}
