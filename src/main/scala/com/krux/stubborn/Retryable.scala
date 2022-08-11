/*
 * Copyright (c) 2018, salesforce.com, inc.
 * All rights reserved.
 * SPDX-License-Identifier: BSD-3-Clause
 * For full license text, see the LICENSE file in the repo root or https://opensource.org/licenses/BSD-3-Clause
 */

package com.krux.stubborn

import scala.util.Try

import org.slf4j.{Logger, LoggerFactory}

import com.krux.stubborn.policy.Policy

trait Retryable { policy: Policy =>

  def logger: Logger = LoggerFactory.getLogger(getClass)

  def maxRetry: Int = Retryable.defaultMaxRetry

  def shouldRetry: PartialFunction[Throwable, Throwable] = Retryable.defaultShouldRetry

  implicit class RetryableImpl[A](action: => A) {

    def retry(): A = Retryable.retry(maxRetry, policy, shouldRetry, logger, 0)(action)

  }

}

object Retryable extends RetryDefaults {

  def defaultShouldRetry: PartialFunction[Throwable, Throwable] = { case e: RuntimeException =>
    e
  }

  def retry[A](
    maxRetry: Int = defaultMaxRetry,
    policy: Policy,
    shouldRetry: PartialFunction[Throwable, Throwable] = defaultShouldRetry,
    logger: Logger = defaultLogger,
    currentAttempt: Int = 0
  )(action: => A): A = {

    if (currentAttempt < maxRetry) {
      try {
        action
      } catch {
        shouldRetry.andThen { e =>
          val delay = policy.retryDelay(currentAttempt)
          logger.info(
            s"Caught exception: ${e.getMessage}\n Retry (Attempt ${currentAttempt}) after $delay milliseconds..."
          )
          Thread.sleep(delay)
          retry(maxRetry, policy, shouldRetry, logger, currentAttempt + 1)(action)
        }
      }
    } else {
      action
    }
  }

  def retryWrapper[A](
    maxRetry: Int = defaultMaxRetry,
    policy: Policy,
    shouldRetry: PartialFunction[Throwable, Throwable] = defaultShouldRetry,
    logger: Logger = defaultLogger,
    currentAttempt: Int = 0
  )(action: => A): Try[A] = {
    Try { retry(maxRetry, policy, shouldRetry, logger, currentAttempt)(action) }
  }

}
