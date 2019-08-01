package persistence.organization.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import persistence.facility.model.Facility
import persistence.organization.model.Organization
import persistence.organization.model.Relation
import persistence.geo.model.Location




// DAO: 組織情報
//~~~~~~~~~~~~~~~~~~
class RelationDAO @javax.inject.Inject()(
  val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  lazy val slick = TableQuery[RelationTable]

  /**
    * 関係性を全件取得
    */

  def findAll =
    db.run {
      slick
        .result
    }


  /**
    * 関係性を取得
    */

  def getRelations(id: Organization.Id) =
    db.run {
      slick
        .filter(_.organizationId === id)
        .result
    }


  /**
    * 関係性を追加
    */

  def add(data: Relation) = {
    db.run(
      data.id match {
        case None    => slick returning slick.map(_.id) += data
        case Some(_) => DBIO.failed(
          new IllegalArgumentException("The given object is already assigned id.")
        )
      }
    )
  }


  /**
    * 関係性を削除
    */

  def delete(organizationId: Organization.Id) = {
    db.run(
      slick
        .filter(_.organizationId === organizationId)
        .delete
    )
  }

  class RelationTable(tag: Tag) extends Table[Relation](tag, "organization_facility") {

    //カラム
    /* @1 */ def id              = column[Relation.Id]      ("id", O.PrimaryKey, O.AutoInc)
    /* @2 */ def organizationId  = column[Organization.Id]  ("organization_id")
    /* @3 */ def facilityId      = column[Facility.Id]      ("facility_id")
    /* @4 */ def createdAt       = column[LocalDateTime]    ("created_at")

    //ここ理解できてないから行った時に聞こーっと
    // The * projection of the table
    def * = (
      id.?, organizationId, facilityId, createdAt
    ) <> (
      /** The bidirectional mappings : Tuple(table) => Model */
      (Relation.apply _).tupled,
      /** The bidirectional mappings : Model => Tuple(table) */
      (v: TableElementType) => Relation.unapply(v).map(_.copy(
        //_7 = LocalDateTime.now
      ))
    )

  }

}