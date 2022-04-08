/*
 * Copyright (c) 2018, salesforce.com, inc.
 * All rights reserved.
 * SPDX-License-Identifier: BSD-3-Clause
 * For full license text, see the LICENSE file in the repo root or https://opensource.org/licenses/BSD-3-Clause
 */

package com.krux.stubborn.policy

trait ExponentialBackoff extends Policy {

  def base: Int = ExponentialBackoff.defaultBase

  def cap: Int = ExponentialBackoff.defaultCap

  def retryDelay(attempt: Int): Int = Math.min(
    cap,
    base * Math.pow(2, (attempt + 1)).toInt
  )

}

object ExponentialBackoff {

  val defaultBase: Int = 3000

  val defaultCap: Int = 60000

  def apply(baseValue: Int = defaultBase, capValue: Int = defaultCap) = new ExponentialBackoff {
    override def base = baseValue
    override def cap = capValue
  }

}
