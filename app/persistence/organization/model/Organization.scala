package persistence.organization.model

import play.api.data._
import play.api.data.Forms._
import java.time.LocalDateTime
//import は中間テーブルも使うかも 複数のfacilityとひもづけるため
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

object Organization {

  type Id = Long

}

