/*
 * This file is part of the MARIAGE services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package model.site.facility

import model.component.util.ViewValuePageLayout
import persistence.geo.model.Location
import persistence.facility.model.Facility
import model.component.util.PaginatedResult

// 表示: 施設一覧
//~~~~~~~~~~~~~~~~~~~~~
case class SiteViewValueFacilityList(
  layout:   ViewValuePageLayout,
  location: Seq[Location],
  facilities: PaginatedResult[Facility]
)
