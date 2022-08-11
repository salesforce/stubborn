package com.krux.stubborn

import org.scalatest.wordspec.AnyWordSpec

import com.krux.stubborn.policy.ExponentialBackoff

class RetryableSpec extends AnyWordSpec {
  private val testPolicy = ExponentialBackoff(300, 600)

  "Retry" when {
    "it runs" should {
      "try to execute N times before failing using opt" in {
        val numRetries = 3
        var numRuns = -1
        Retryable.retryWrapper(maxRetry = numRetries, policy = testPolicy) {
          numRuns += 1
          throw new RuntimeException("Throw")
        }
        assert(numRetries === numRuns)
      }

      "Return function's value in success case using either" in {
        val expected = 1
        val actual = Retryable.retryWrapper(policy = testPolicy) {
          expected
        }
        assert(actual.get == expected)
      }
    }
  }
}
