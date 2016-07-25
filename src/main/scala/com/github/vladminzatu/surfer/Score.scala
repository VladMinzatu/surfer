package com.github.vladminzatu.surfer


import scala.math._

class Score(val value: Double) extends Serializable {

  def +(time: Long): Score = {
    val u = max(value, Score.rate * time)
    val v = min(value, Score.rate * time)
    Score(u + log1p(exp(v - u)))
  }

  override def toString(): String = value.toString;
}

object Score{

  def apply(value: Double) = new Score(value)

  /**
   * @param time milliseconds since the epoch
   */
  def apply(time: Long) = new Score(rate * time)

  def rate = 1.0 / 60000 /*(24 * 3.6e6)*/ // one day
}
