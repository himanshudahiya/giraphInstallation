package btp;

import org.apache.giraph.graph.BasicComputation;
import org.apache.giraph.conf.LongConfOption;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.graph.Vertex;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.apache.giraph.edge.Edge;
import org.apache.giraph.edge.EdgeFactory;
import org.apache.giraph.io.EdgeReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

//import org.apache.giraph.utils.ArrayWritable;
import org.apache.hadoop.io.ArrayWritable;
public class EdgeInputFormat extends
org.apache.giraph.io.formats.TextEdgeInputFormat<LongWritable,
Text> {

public EdgeInputFormat() {

super();

}

protected class mTextEdgeReaderFromEachLine extends

TextEdgeReaderFromEachLine {

protected LongWritable getSourceVertexId(org.apache.hadoop.io.Text
line) {

return new LongWritable(

Long.parseLong(line.toString().split(":")[0]));

}


protected LongWritable getTargetVertexId(org.apache.hadoop.io.Text
line) {

return new LongWritable(

Long.parseLong(line.toString().split(":")[1]));

}


protected Text getValue(org.apache.hadoop.io.Text line) {
	String str = line.toString().split(":")[2];
	Text t1 = new Text(str);	
	 System.out.println(t1);
	return t1;
	
	
//	String arr[] = str.split(",");
//	int len = arr.length;
//	LongWritable[] array = new LongWritable[len];
//	
//	for(int i=0;i<len;i++) {
//		array[i] =  new LongWritable( Long.parseLong(arr[i]));
//	}
	
	//ArrayWritable aw = new ArrayWritable((Class<? extends Writable>) EdgeInputFormat.class,array);
	//aw.set(array);

	
	//Arrays.stream(str.split(",")).map(String::trim);
	//.mapToInt(Integer::parseInt).toArray();

	

}

}


public EdgeReader<LongWritable, Text> createEdgeReader(

InputSplit split, TaskAttemptContext context) throws IOException {

return new mTextEdgeReaderFromEachLine();

}

}
