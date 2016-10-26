package mappers;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StopWordsMapper extends Mapper<LongWritable,Text,Text,IntWritable>{

	public void map(LongWritable key,Text record,Context context)
						throws IOException, InterruptedException{

		

        // get first char of the line from stopWords file
        String a = String.valueOf(record.charAt(0));

        // this will make sure that the words starting with a particular
        // character, how many times they have been displayed in stop file
        context.write(new Text(a),new IntWritable(1));
	}
	
}
