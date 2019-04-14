package com.safecharge.finalproject.services;

import com.safecharge.finalproject.UserConf;
import com.safecharge.finalproject.WordsUtil;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.spark.sql.functions.*;

/**
 * @author Evgeny Borisov
 */
@Service
public class JudgeService implements Serializable {

    public static final String WORD = "word";
    private static final String AMOUNT = "amount";
    @Autowired
    private  transient JavaSparkContext sc;

    @Autowired
    private transient SQLContext sqlContext;

    @Autowired
    private SparkSession sparkSession;

    @Autowired
    private Broadcast<UserConf> userConfBroadcast;

    public Map<String, Long> topWords(String artist, int x) {
        JavaRDD<String> rdd = sc.textFile("songs/" + artist + "/*");
        JavaRDD<Row> wordsRdd = rdd.map(String::toLowerCase)
                .flatMap(WordsUtil::getWords)
                .filter(word->this.userConfBroadcast.value().isNotGarbage(word))
                .map(RowFactory::create);

        Dataset<Row> dataFrame = sparkSession.createDataFrame(wordsRdd, buildSchema());


        Dataset<Row> dataset = dataFrame.groupBy(col(WORD)).agg(count(col(WORD)).as(AMOUNT))
                .orderBy(col(AMOUNT).desc());
        List<Row> rows1 = dataset.takeAsList(x);
        Map<String, Long> map = rows1.stream().collect(Collectors.toMap(row -> (String) row.getAs(WORD), row -> (Long) row.getAs(AMOUNT)));

        return map;


    }


    private StructType buildSchema() {
        return DataTypes.createStructType(new StructField[]{
                        DataTypes.createStructField(WORD, DataTypes.StringType, false),
                }
        );
    }

}




