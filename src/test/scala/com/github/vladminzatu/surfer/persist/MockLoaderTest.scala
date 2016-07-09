package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.TestBase
import org.apache.spark.{SparkConf, SparkContext}

class MockLoaderTest extends TestBase {

  private var sc: SparkContext = _

  before{
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("test")

    sc = new SparkContext(conf)
  }

  after {
    if (sc != null) {
      sc.stop()
    }
  }

  test("The MockLoader always 'loads' the same RDD with a specific content"){
    val rdd = new MockLoader(sc).load()
    rdd.collect().map(p => p._1) should be (Array(("a"), ("b"), ("c")))
  }
}