package com.github.vladminzatu.surfer.persist

import com.github.vladminzatu.surfer.Score
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{HttpClientBuilder}
import org.apache.spark.rdd.RDD
import org.json4s.jackson.Serialization.write

case class Item(item:String, score:Double)

class RestPersister extends Persister {

  val url = "http://localhost:8080/items"

  override def persist(scores: RDD[(String, Score)]): Unit = {
    implicit val formats = org.json4s.DefaultFormats
    val payload = write(scores.collect().sortWith((a,b) => a._2.value > b._2.value).map(x => Item(x._1, x._2.value)))
    println(payload)
    val client = HttpClientBuilder.create().build();
    client.execute(postRequest(payload))
  }

  private def postRequest(payload: String): HttpPost = {
    val post = new HttpPost(url)
    post.setEntity(new StringEntity(payload))
    post
  }
}