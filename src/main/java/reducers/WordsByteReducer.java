package reducers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class WordsByteReducer extends Reducer<Text,Text,NullWritable,Text>{


	
	@Override
	public void reduce(Text key,Iterable<Text> records,Context context) 
					throws IOException, InterruptedException{
	
		/*
		 *  here, you'll get the data in this form < be, [123,321,123] > 
		 *  where key is the word itself and value is an array of byte sizes
		 *  of the lines in which they appeared.
		 *  
		 */
		boolean exists = compareStopWords(context,key.toString());
		
		if(exists){
			return;
		}

		String line = key.toString()+"=";
		for(Text record:records){
			line = line + record + ",";
		}

		context.write(NullWritable.get(),new Text(line));

	}
	
	/*
	 * This will read the file which has the line numbers mentioned for 
	 * each letter.
	 */
	public boolean compareStopWords(Context context,String charac) throws IOException{
		
		
		Path pt=new Path("/user/cloudera/english.stop.txt");
		FileSystem fs = FileSystem.get(context.getConfiguration());
		BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
		try {
			int sum  =0;
		  String line;
		  line=br.readLine();
		  while (line != null){
			
			if(line == charac){
				return true;
			}
			  
		    line = br.readLine();
		  }
		} finally {
		  // you should close out the BufferedReader
		  br.close();
		}
		
		return false;
	}
}
