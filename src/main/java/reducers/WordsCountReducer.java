package reducers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class WordsCountReducer extends Reducer<IntWritable,Text,NullWritable,Text>{

	@Override
	public void reduce(IntWritable key,Iterable<Text> records,Context context) 
					throws IOException, InterruptedException{

		int sum = 0;
		String line = "";
		for(Text record:records){
			sum+=1;
			line = "("+record.toString();
		}
		
		line = line + ","+String.valueOf(sum);
		
		context.write(NullWritable.get(), new Text(line));
		
	}
}