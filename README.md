# Clustering
Java KMeans clustering algorithm


		 SparkConf conf = new SparkConf().set("spark.eventLog.enabled", "true").set("spark.eventLog.dir","/Users/alicanbalik/Desktop").set("spark.cassandra.connection.host", "127.0.0.1").setAppName("alican-app").setMaster("spark://Alicans-MacBook-Pro.local:7077");

set("spark.eventLog.enabled", "true") - To view the web UI after the fact, set spark.eventLog.enabled to true before starting the application. This configures Spark to log Spark events that encode the information displayed in the UI to persisted storage.
setAppName("alican-app") - Application name. It's better to give the same name as the project name.
set("spark.eventLog.dir","/Users/alicanbalik/Desktop") - destination of the eventLog.
setMaster("spark://Alicans-MacBook-Pro.local:7077"); - Spark running port. You can copy your link from Apache Spark homepage when you run it.



<dependencies>
    <dependency>
        <groupId>com.sparkjava</groupId>
        <artifactId>spark-core</artifactId>
        <version>2.3</version>
    </dependency>
    <dependency>
    	<groupId>com.datastax.spark</groupId>
    	<artifactId>spark-cassandra-connector_2.10</artifactId>
    	<version>1.6.0</version>
	</dependency>
    <dependency>
    	<groupId>org.apache.spark</groupId>
    	<artifactId>spark-mllib_2.10</artifactId>
    	<version>1.6.1</version>
	</dependency>
	<dependency>
    	<groupId>org.apache.spark</groupId>
    	<artifactId>spark-sql_2.10</artifactId>
    	<version>1.6.1</version>
	</dependency>	
</dependencies>
