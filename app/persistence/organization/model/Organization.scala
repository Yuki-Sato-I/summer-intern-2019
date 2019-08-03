package persistence.organization.model

import play.api.data._
import play.api.data.Forms._
import java.time.LocalDateTime
import persistence.geo.model.Location


case class Organization(
  id:            Option[Organization.Id],               //組織id
  locationId:    Location.Id,                           //地域id
  enName:        String,                                //英語名
  kanziName:     String,                                //漢字名
  phoneticName:  String,                                //ふりがな
  address:       String,                                //住所詳細
  updatedAt:     LocalDateTime = LocalDateTime.now,
  createdAt:     LocalDateTime = LocalDateTime.now
)



case class OrganizationEdit(
   locationId:   Option[Location.Id],
   enName:       String,
   kanziName:    String,
   phoneticName: String,
   address:      String
)

object Organization {

  type Id = Long


  val formForOrganizationEdit = Form(
    mapping(
      "locationId"   -> optional(text),
      "enName"       -> nonEmptyText,
      "kanziName"    -> nonEmptyText,
      "phoneticName" -> nonEmptyText,
      "address"      -> nonEmptyText,
    )(OrganizationEdit.apply)(OrganizationEdit.unapply)
  )
  val formForNewOrganization = Form(
    mapping(
      "locationId"   -> text,
      "enName"       -> nonEmptyText,
      "kanziName"    -> nonEmptyText,
      "phoneticName" -> nonEmptyText,
      "address"      -> nonEmptyText,
    )(Function.untupled(
      t => Organization(None, t._1, t._2, t._3, t._4, t._5)
    ))(Organization.unapply(_).map(
      t => (t._2, t._3, t._4, t._5, t._6)
    ))
  )
}

