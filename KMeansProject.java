import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.feature.Word2Vec;
import org.apache.spark.mllib.feature.Word2VecModel;


public class KMeansProject {
	
	

	public static void main(String[] args) {
		 SparkConf conf = new SparkConf().set("spark.eventLog.enabled", "true").set("spark.eventLog.dir","/Users/alicanbalik/Desktop").set("spark.cassandra.connection.host", "127.0.0.1").setAppName("alican-app").setMaster("spark://Alicans-MacBook-Pro.local:7077");
			JavaSparkContext jsc = new JavaSparkContext(conf);
		    // Load and parse data
			// You can paste direct path of the data in textFile("direction/of/data"); if you don't want to use args[].
		    JavaRDD<String> data = jsc.textFile(args[0]);
		    JavaRDD<Vector> parsedData = data.map(
		      new Function<String, Vector>() {
		        public Vector call(String s) {
		          String[] sarray = s.split(","); //(?<=\\G.{2})
		          for (int i = 0; i < sarray.length; i++) {
					System.out.println(sarray[i]);
				}
		          
		          double[] values = new double[sarray.length];
		          for (int i = 0; i < sarray.length; i++) {
		            values[i] = Double.parseDouble(sarray[i]);
		          }
		          return Vectors.dense(values);
		        }
		      }
		    );
		    parsedData.cache();

		    // Cluster the data into two classes
		    int numClusters = 2;
		    int numIterations = 999;
		    KMeansModel clusters = KMeans.train(parsedData.rdd(), numClusters, numIterations);
		    System.out.println("***************************** Cluster centers: *****************************");
		    for (Vector center: clusters.clusterCenters()) {
		      System.out.println(" " + center);
		    }
		    double cost = clusters.computeCost(parsedData.rdd());
		    System.out.println("***************************** Cost: " + cost);

		    // Evaluate clustering by computing Within Set Sum of Squared Errors
		    double WSSSE = clusters.computeCost(parsedData.rdd());
		    System.out.println("***************************** Within Set Sum of Squared Errors = " + WSSSE);
		    
		    // Save and load model
		    clusters.save(jsc.sc(), "/Users/alicanbalik/Desktop/KMeansProject");
		    KMeansModel sameModel = KMeansModel.load(jsc.sc(),
		      "/Users/alicanbalik/Desktop/KMeansProject");

		    jsc.stop();
	}
}
