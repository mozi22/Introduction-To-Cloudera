package mappers;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/*
 * The job of this mapper is to just output values in the following form
 * <count,1>
 * 
 * This will help us to combine the result in the reduce section by simply
 * adding all the counts. 
 * 
 * Since we're specifying a static key here. So the reduce will be only called
 * once resulting in all the 1's adding up to the number of lines.
 */

public class CountMapper extends Mapper<LongWritable,Text,Text,IntWritable>{

	public void map(LongWritable key,Text record,Context context)
						throws IOException, InterruptedException{

		context.write(new Text("count"), new IntWritable(1));
	}
	
}
