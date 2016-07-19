package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.streaming.dstream.{MapWithStateDStream, DStream}

abstract class PersistenceMode(val persister: Persister) {

  def apply(scores: MapWithStateDStream[String, Long, Score, (String, Score)]): Unit
}
