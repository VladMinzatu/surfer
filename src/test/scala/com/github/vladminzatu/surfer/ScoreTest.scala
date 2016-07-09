package com.github.vladminzatu.surfer

import scala.math._

class ScoreTest extends TestBase {

  test("Initializing with a value"){
    val score = Score(1.0)
    score.value should be (1.0)
  }

  test("Initializing from timestamp"){
    val score = Score(1467142218000L)
    score.value should === (Score.rate * 1467142218000L)
  }

  test("Rate is defined in the companion object"){
    Score.rate should === (1.0 / (24 * 3.6e6))
  }

  test("Adding a new timestamp to the score"){
    val time1 = 1467140742000L
    val score = Score(1467140741000L)
    val newScore1 = score + time1
    newScore1.value should === (Score.rate * time1 + log1p(exp(score.value - Score.rate * time1)))

    val time2 = 1467140743000L
    val newScore2 = newScore1 + time2
    newScore2.value should === (Score.rate * time2 + log1p(exp(newScore1.value - Score.rate * time2)))
  }

  test("Score is rendered as its value"){
    val score = Score(1.0)
    score.toString should be (score.value.toString)
  }
}