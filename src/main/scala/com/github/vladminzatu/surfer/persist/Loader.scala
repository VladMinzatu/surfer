package com.github.vladminzatu.surfer.persist


import com.github.vladminzatu.surfer.Score
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

abstract class Loader(val sc: SparkContext) {

  def load(): RDD[(String, Score)]
}
