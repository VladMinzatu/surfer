package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.streaming.dstream.MapWithStateDStream

class UpdatePersistenceStrategy(persister: Persister) extends PersistenceStrategy(persister) {

  override def apply(scores: MapWithStateDStream[String, Long, Score, (String, Score)]): Unit = {
    scores.foreachRDD(rdd => persister.persist(rdd.reduceByKey((s1, s2) => if(s1.value < s2.value) s2 else s1)))
  }
}
