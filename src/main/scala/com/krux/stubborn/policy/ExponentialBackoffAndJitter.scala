/*
 * Copyright (c) 2018, salesforce.com, inc.
 * All rights reserved.
 * SPDX-License-Identifier: BSD-3-Clause
 * For full license text, see the LICENSE file in the repo root or https://opensource.org/licenses/BSD-3-Clause
 */

package com.krux.stubborn.policy

import scala.util.Random

/**
 * Implements the FullJitter algorithm in
 * https://www.awsarchitectureblog.com/2015/03/backoff.html
 */
trait ExponentialBackoffAndJitter extends ExponentialBackoff with Policy {

  override def retryDelay(attempt: Int): Int = Random.nextInt(super.retryDelay(attempt))

}

object ExponentialBackoffAndJitter {

  def apply(
    baseValue: Int = ExponentialBackoff.defaultBase,
    capValue: Int = ExponentialBackoff.defaultCap
  ) = new ExponentialBackoffAndJitter {
    override def base = baseValue
    override def cap = capValue
  }

}
