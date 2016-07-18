package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.rdd.RDD

class MockPersister extends Persister {

  override def persist(scores: RDD[(String, Score)]): Unit = {
    println(scores.collect().deep)
  }
}