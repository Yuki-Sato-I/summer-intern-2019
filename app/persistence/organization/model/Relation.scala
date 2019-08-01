package persistence.organization.model

import play.api.data._
import play.api.data.Forms._
import java.time.LocalDateTime


import persistence.geo.model.Location
import persistence.facility.model.Facility



case class Relation(
  id:               Option[Relation.Id],
  organizationId:  Organization.Id,
  facilityId:      Facility.Id,
  createdAt:        LocalDateTime = LocalDateTime.now
)

case class newRelation(
  organizationId:  Organization.Id,
  facilityId:      Facility.Id,
)

case class RelationsForm(
  relations: List[Relation]
)

object Relation {

  type Id = Long

  val formForNewRelation = Form(
    mapping(
      "relations" -> list(mapping(
        "organization_id"   -> longNumber,
        "facility_id"       -> longNumber,
      )(Function.untupled(
        t => Relation(None, t._1, t._2)))(
        Relation.unapply(_).map(
        t => (t._2, t._3)
      )))
    )(RelationsForm.apply)(RelationsForm.unapply)
  )
}