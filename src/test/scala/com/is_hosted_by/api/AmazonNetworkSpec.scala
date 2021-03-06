// Copyright 2015-2016 Ricardo Gladwell.
// Licensed under the GNU Affero General Public License.
// See the LICENSE file for more information.

package com.is_hosted_by.api

import org.specs2.mutable.Specification
import org.specs2.specification.Scope
import test.TestConfiguration
import org.specs2.matcher.FutureMatchers
import net.CidrNotationIpPrefix

object AmazonNetworkSpec extends Specification with Mocks with FutureMatchers {

  class TestAmazonNetwork extends AmazonNetwork with TestConfiguration with Scope

  "AmazonNetwork" should {
    "load IP ranges from a URL" in new TestAmazonNetwork {
      networks().map { _.head.ipRanges must contain (CidrNotationIpPrefix("50.19.0.0/16")) }.await
    }
  }

}
