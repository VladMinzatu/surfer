package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.rdd.RDD

/**
 * Print items in descending order to console.
 * Useful for testing.
 */
class MockPersister extends Persister {

  override def persist(scores: RDD[(String, Score)]): Unit = {
    println(scores.collect().sortWith((a,b) => a._2.value > b._2.value).deep)
  }
}