package mappers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class CountMapper extends Mapper<LongWritable,Text,Text,IntWritable>{

	public void map(LongWritable key,Text record,Context context)
						throws IOException, InterruptedException{

		context.write(new Text("count"), new IntWritable(1));
	}
	
}
