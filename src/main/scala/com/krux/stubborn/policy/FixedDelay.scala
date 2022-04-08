/*
 * Copyright (c) 2018, salesforce.com, inc.
 * All rights reserved.
 * SPDX-License-Identifier: BSD-3-Clause
 * For full license text, see the LICENSE file in the repo root or https://opensource.org/licenses/BSD-3-Clause
 */

package com.krux.stubborn.policy

trait FixedDelay extends Policy {

  def fixedDelay: Int = FixedDelay.defaultFixedDelay

  def retryDelay(attempt: Int): Int = fixedDelay

}

object FixedDelay {

  val defaultFixedDelay: Int = 3000

  def apply(fixedDelayValue: Int = defaultFixedDelay) = new FixedDelay {
    override def fixedDelay = defaultFixedDelay
  }

}
