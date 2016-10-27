package mappers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordsByteMapper   extends Mapper<LongWritable,Text,Text,Text>{

	public void map(LongWritable key,Text record,Context context)
						throws IOException, InterruptedException{

		
        Text linesize = new Text();
        Text text = new Text();
        StringTokenizer tokenizer = new StringTokenizer(record.toString());
        linesize.set(getStringByteSize(record.toString()));

        while(tokenizer.hasMoreElements()){
            String token = tokenizer.nextToken().toLowerCase();
            text.set(token);

            /*
             *  this will save the data in the following format
             *  < word , bytesize >
             *  
             *  Note that the bytesize will be same for all the words in this
             *  line, since we're calculating the bytesize of whole line.
             */
            context.write(text,linesize);
        }

	}

	// the function to calculate the bytesize of a string.
	public String getStringByteSize(String string) throws UnsupportedEncodingException{
		
		final byte[] utf8Bytes = string.getBytes("UTF-8");
		return String.valueOf(utf8Bytes.length);
	}
	
	
}
