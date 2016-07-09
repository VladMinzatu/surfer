package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.rdd.RDD

abstract class StatePersister extends Serializable {

  def persist(scores: RDD[(String, Score)]): Unit
}
