package com.safecharge.finalproject;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Evgeny Borisov
 */
@Configuration
public class SparkConfiguration {

    @Bean
    public Broadcast<UserConf> userConfBroadcast(UserConf userConf){
        Broadcast<UserConf> broadcast = sc().broadcast(userConf);
        return broadcast;
    }

    @Bean
    public SQLContext sqlContext(){
        return new SQLContext(sc().sc());
    }

    @Bean
    public SparkSession sparkSession(){
        return new SparkSession(sc().sc());
    }

    @Bean
    public JavaSparkContext sc(){
        SparkConf sparkConf = new SparkConf().setMaster("local[*]").setAppName("music");
        return new JavaSparkContext(sparkConf);
    }
}
