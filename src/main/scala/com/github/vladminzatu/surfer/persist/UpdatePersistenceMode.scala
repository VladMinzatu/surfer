package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.streaming.dstream.MapWithStateDStream

class UpdatePersistenceMode(val updater: Updater) extends PersistenceMode {

  override def apply(scores: MapWithStateDStream[String, Long, Score, (String, Score)]): Unit = {
    scores.foreachRDD(rdd => updater.update(rdd.reduceByKey((s1, s2) => if(s1.value < s2.value) s2 else s1)))
  }
}
