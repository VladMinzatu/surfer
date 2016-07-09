package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
 * Loads a simple hardcoded rdd for testing
 */
class MockLoader(sc: SparkContext) extends Loader(sc) {

  override def load(): RDD[(String, Score)] = {
    val score = Score(System.currentTimeMillis)
    sc.parallelize(Array(("a", score), ("b", score), ("c", score)))
  }
}
