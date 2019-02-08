package btp;

import java.net.URI;

import org . apache . giraph . conf . GiraphConfiguration ;
import btp.ShortestPath;
 import org . apache . giraph . io . formats .*;
import org . apache . giraph . job . GiraphJob ;
 import org . apache . hadoop . conf . Configuration ;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org . apache . hadoop . fs . Path ;
 import org . apache . hadoop . mapreduce . lib . output .
FileOutputFormat ;
 import org . apache . hadoop . util . Tool ;
import org . apache . hadoop . util . ToolRunner ;
import btp.EdgeInputFormat;
public class Demorunner implements Tool {
 private Configuration conf ;
 
 public Configuration getConf () {
return conf ;
 }
 public void setConf ( Configuration conf ) {
	 conf.addResource(new Path("/usr/local/hadoop/etc/hadoop/core-site.xml"));
	 conf.addResource(new Path("/usr/loca/hadoop/etc/hadoop/hdfs-site.xml"));
//	 conf.set("fs.default.name", "hdfs://hdnode03:50070");
 this . conf = conf ;
 }

 public int run ( String [] arg0 ) throws Exception {
	 
 String inputPath ="/tmp/tiny_graph.txt";
 String outputPath ="/tmp/graph_out56";
 
 URI uri = URI.create ("hdfs://hdnode03:50070/home/lab/tiny_graph.txt");
 
 
 
 GiraphConfiguration giraphConf = new GiraphConfiguration ( getConf () ) ;
 giraphConf.setComputationClass (ShortestPath.class ) ;
 giraphConf.setEdgeInputFormatClass(EdgeInputFormat.class);
// giraphConf.setVertexInputFormatClass (EdgeInputFormat.class) ;
 GiraphFileInputFormat.addEdgeInputPath( giraphConf ,new Path ( uri ) ) ;

 giraphConf . setVertexOutputFormatClass(IdWithValueTextOutputFormat.class) ;
 giraphConf . setLocalTestMode ( false ) ;
 giraphConf . setWorkerConfiguration (1 , 1 , 100) ;
 giraphConf . SPLIT_MASTER_WORKER . set ( giraphConf , false );

 InMemoryVertexOutputFormat . initializeOutputGraph (giraphConf) ;

 GiraphJob giraphJob = new GiraphJob ( giraphConf ,"GiraphDemo ") ;

 FileOutputFormat . setOutputPath ( giraphJob .
getInternalJob () , new Path ( outputPath ) ) ;

 giraphJob . run ( true ) ;
 return 0;
 }

 public static void main ( String [] args ) throws Exception {
 ToolRunner . run ( new Demorunner () , args ) ;
 }
 }
