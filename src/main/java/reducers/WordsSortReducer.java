package reducers;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;

import java.io.IOException;

public class WordsSortReducer extends Reducer<IntWritable,Text,NullWritable,Text>{

	@Override
	public void reduce(IntWritable key,Iterable<Text> records,Context context) 
					throws IOException, InterruptedException{

		String line = "";
		for(Text record:records){
			line+= record+" ";
		}
		context.write(NullWritable.get(), new Text(line));
		
	}
}
 


