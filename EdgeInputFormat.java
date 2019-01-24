public class EdgeInputFormat extends
org.apache.giraph.io.formats.TextEdgeInputFormat<LongWritable,
LongWritable> {

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


protected LongWritable getValue(org.apache.hadoop.io.Text line) {

return new LongWritable(

Long.parseLong(line.toString().split(":")[2]));

}

}


public EdgeReader<LongWritable, LongWritable> createEdgeReader(

InputSplit split, TaskAttemptContext context) throws IOException {

return new mTextEdgeReaderFromEachLine();

}

}
