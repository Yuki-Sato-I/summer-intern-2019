package persistence.organization.dao

import java.time.LocalDateTime
import scala.concurrent.Future

import slick.jdbc.JdbcProfile
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider

import persistence.geo.model.Location
import persistence.organization.model.Organization
import persistence.organization.model.OrganizationEdit

import scala.concurrent.ExecutionContext.Implicits.global
import model.component.util.PaginatedResult

import scala.math.ceil //計算用
// DAO: 組織情報
//~~~~~~~~~~~~~~~~~~
class OrganizationDAO @javax.inject.Inject()(
  val dbConfigProvider: DatabaseConfigProvider
) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  lazy val slick = TableQuery[OrganizationTable]

  /**
    * 組織を全件取得
    */

  def findAll =
    db.run {
      slick
        .result
    }

  /**
    *
    * @param limit  1ページあたりのデータ数
    * @param offset 先頭から何個データを抜かすか
    * @param page   現在のページ
    * @return       PaginateResult
    */
  def findWithPagenate(limit: Int, offset: Int, page: Int) =
    db.run {
      for {
        organizations       <-  slick.drop(offset).take(limit).result
        numOfOrganizations  <-  slick.length.result
      } yield PaginatedResult(
        totalCount = numOfOrganizations,
        pageCount  = ceil(numOfOrganizations.toDouble/limit.toDouble).toInt,
        currentPage = page,
        entities = organizations.toList,
        hasNextPage = numOfOrganizations - (offset + limit) > 0,
        hasPreviousPage = offset != 0
      )

    }


  /**
    * 組織を取得
    */

  def get(id: Organization.Id) =
    db.run {
      slick
        .filter(_.id === id)
        .result.headOption
    }

  /**
    * 組織を編集
    */

  def update(id: Organization.Id, form: OrganizationEdit) = {
    db.run(
      slick
        .filter(_.id === id)
        .map(p => (p.locationId, p.enName, p.kanziName, p.phoneticName, p.address))
        .update((form.locationId.get, form.enName, form.kanziName, form.phoneticName, form.address))
    )
  }


  /**
    * 組織を追加
    */

  def add(data: Organization) = {
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
    * 組織を削除
    */

  def delete(id: Organization.Id) = {
    db.run(
      slick
        .filter(_.id === id)
        .delete
    )
  }

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