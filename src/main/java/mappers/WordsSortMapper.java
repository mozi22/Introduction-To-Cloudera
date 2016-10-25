package mappers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordsSortMapper  extends Mapper<LongWritable,Text,IntWritable,Text>{

	public void map(LongWritable key,Text record,Context context)
						throws IOException, InterruptedException{

		
        IntWritable writableCount = new IntWritable();
        Text text = new Text();
        StringTokenizer tokenizer = new StringTokenizer(record.toString());

        while(tokenizer.hasMoreElements()){
            String token = tokenizer.nextToken();

            int ascii = (int)token.toLowerCase().charAt(0);
            
            writableCount.set(ascii);
            text.set(token);

            // set the ascii value of the first letter of word as 'key'
            // set the text itself as the 'value'
            context.write(writableCount,text);
        }

	}
	
}
