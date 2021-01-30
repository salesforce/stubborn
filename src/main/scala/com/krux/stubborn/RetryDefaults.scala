/*
 * Copyright (c) 2018, salesforce.com, inc.
 * All rights reserved.
 * SPDX-License-Identifier: BSD-3-Clause
 * For full license text, see the LICENSE file in the repo root or https://opensource.org/licenses/BSD-3-Clause
 */

package com.krux.stubborn

import org.slf4j.{Logger, LoggerFactory}

import com.krux.stubborn.policy.{Policy, ExponentialBackoffAndJitter}


trait RetryDefaults {

  def defaultLogger: Logger = LoggerFactory.getLogger(getClass)

  val defaultMaxRetry = 3

  def defaultPolicy: Policy = new ExponentialBackoffAndJitter{}

}
