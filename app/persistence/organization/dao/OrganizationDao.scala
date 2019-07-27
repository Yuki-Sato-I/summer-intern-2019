package persistence.organization.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import persistence.facility.model.Facility
import persistence.geo.model.Location
import persistence.organization.model.Organization



// DAO: 組織情報
//~~~~~~~~~~~~~~~~~~
class FacilityDAO @javax.inject.Inject()(
  val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  lazy val slick = TableQuery[OrganizationTable]


  class OrganizationTable(tag: Tag) extends Table[Organization](tag, "organization") {

    //カラム
    /* @1 */ def id           = column[Organization.Id] ("id", O.PrimaryKey, O.AutoInc)
    /* @2 */ def locationId   = column[Location.Id]     ("location_id")
    /* @3 */ def enName       = column[String]          ("en_name")
    /* @4 */ def kanziName    = column[String]          ("kanzi_name")
    /* @5 */ def phoneticName = column[String]          ("phonetic_name")
    /* @6 */ def address      = column[String]          ("address")
    /* @7 */ def updatedAt    = column[LocalDateTime]   ("updated_at")
    /* @8 */ def createdAt    = column[LocalDateTime]   ("created_at")

    //ここ理解できてないから行った時に聞こーっと
    // The * projection of the table
    def * = (
      id.?, locationId, enName, kanziName, phoneticName, address, updatedAt, createdAt
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (Organization.apply _).tupled,
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => Organization.unapply(v).map(_.copy(
        _7 = LocalDateTime.now
      ))
    )

  }

}