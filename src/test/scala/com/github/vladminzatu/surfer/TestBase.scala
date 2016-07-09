package com.github.vladminzatu.surfer

import org.scalatest.{BeforeAndAfter, Matchers, FunSuite}

/**
 * Base class for all test
 */
abstract class TestBase extends FunSuite with Matchers with BeforeAndAfter
