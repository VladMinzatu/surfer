package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.rdd.RDD

class Updater {

  def update(scores: RDD[(String, Score)]): Unit = {}
}
