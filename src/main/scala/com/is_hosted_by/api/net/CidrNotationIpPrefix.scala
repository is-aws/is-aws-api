// Copyright 2015-2016 Ricardo Gladwell.
// Licensed under the GNU Affero General Public License.
// See the LICENSE file for more information.

package com.is_hosted_by.api.net

import java.net.InetAddress
import net.ripe.commons.ip._

case class CidrNotationIpPrefix(notation: String) extends IpPrefix {

  val prefix =
    try {
      new Ipv4RangePrefix(Ipv4Range.parse(notation))
    } catch {
        case e: Exception => try {
          new Ipv6RangePrefix(Ipv6Range.parse(notation))
        } catch {
          case _: Throwable => throw e
        }
    }

  override def inRange(ip: InetAddress) = prefix.inRange(ip)

}
