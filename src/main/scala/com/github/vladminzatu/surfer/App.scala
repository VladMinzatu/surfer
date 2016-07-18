package com.github.vladminzatu.surfer

import com.github.vladminzatu.surfer.persist.{MockPersister, SnapshotPersistenceMode, MockStatePersister, MockLoader}
import org.apache.spark._
import org.apache.spark.streaming._

object App {

  val checkpointDir = "~/spark-checkpoint"

  val mappingFunc = (key: String, timestamp: Option[Long], state: State[Score]) => {
    if(timestamp.isDefined){
      val score =  state.getOption.getOrElse(Score(timestamp.get)) + timestamp.get
      val output = (key, score)
      state.update(score)
      output
    } else{
      (key, state.get)
    }
  }

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[*]").setAppName("HotItems")
    val ssc = new StreamingContext(conf, Seconds(1))

    val loader = new MockLoader(ssc.sparkContext)
    val persistenceMode = new SnapshotPersistenceMode(new MockPersister)

    ssc.checkpoint(checkpointDir)

    val events = ssc.socketTextStream("localhost", 9999)
    val scores = events.map(x => (x, System.currentTimeMillis))

    val initialRdd = loader.load()

    val stateDstream = scores.mapWithState(
      StateSpec.function(mappingFunc).initialState(initialRdd).timeout(Minutes(60 * 24 * 10)))

    stateDstream.checkpoint(Seconds(30))
    persistenceMode.apply(stateDstream)

    ssc.start()
    ssc.awaitTermination()
  }
}

