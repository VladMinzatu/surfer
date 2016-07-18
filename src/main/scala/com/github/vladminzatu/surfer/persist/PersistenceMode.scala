package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.streaming.dstream.{MapWithStateDStream, DStream}

trait PersistenceMode {

  def apply(scores: MapWithStateDStream[String, Long, Score, (String, Score)]): Unit
}
