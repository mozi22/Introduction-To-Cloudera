package msp_exercise.exercise;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import mappers.CountMapper;
import reducers.CountReducer;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

import org.apache.hadoop.fs.Path;



public class RowCount extends Configured implements Tool{

	public static void main(String[] args) throws Exception{
		int exitCode = ToolRunner.run(new RowCount(), args);
		System.exit(exitCode);
	}
	
	public int run(String[] args) throws Exception {
		
		Job job = Job.getInstance(getConf(),
				"Words count using built in mappers and reducers");
		
		job.setJarByClass(getClass());
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		
		job.setMapperClass(CountMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setReducerClass(CountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		
		
		return job.waitForCompletion(true) ? 0 : 1;
	}

}
