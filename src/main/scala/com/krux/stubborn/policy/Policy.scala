/*
 * Copyright (c) 2018, salesforce.com, inc.
 * All rights reserved.
 * SPDX-License-Identifier: BSD-3-Clause
 * For full license text, see the LICENSE file in the repo root or https://opensource.org/licenses/BSD-3-Clause
 */

package com.krux.stubborn.policy


trait Policy {

  /**
   * @param attempt the current count of the retry
   *
   * @return the number of milliseconds to wait for the next retry
   */
  def retryDelay(attempt: Int): Int

}
