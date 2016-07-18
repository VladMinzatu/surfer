package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.spark.streaming.dstream.MapWithStateDStream

class SnapshotPersistenceMode(val persister: Persister) extends PersistenceMode {

  override def apply(scores: MapWithStateDStream[String, Long, Score, (String, Score)]): Unit = {
    scores.stateSnapshots().foreachRDD(persister.persist _)
  }
}
