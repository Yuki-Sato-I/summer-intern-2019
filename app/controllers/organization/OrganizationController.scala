package controllers.organization

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import model.site.organization.SiteViewValueOrganizationList
import model.site.organization.SiteViewValueOrganizationShow

import model.component.util.ViewValuePageLayout

import persistence.organization.model.Organization
import persistence.organization.dao.OrganizationDAO
import persistence.organization.dao.RelationDAO

import persistence.organization.model.Organization.formForNewOrganization
import persistence.organization.model.OrganizationEdit
import persistence.organization.model.Organization.formForOrganizationEdit
import model.site.organization.SiteViewValueOrganizationEdit

import persistence.organization.model.Relation.formForNewRelation

//まだ使うかどうかわからない
import persistence.facility.model.Facility
import persistence.facility.dao.FacilityDAO
import persistence.geo.model.Location
import persistence.geo.dao.LocationDAO

// 施設
//~~~~~~~~~~~~~~~~~~~~~
class OrganizationController @javax.inject.Inject()(
  val organizationDao: OrganizationDAO,
  val facilityDao: FacilityDAO,
  val daoLocation: LocationDAO,
  val relationDao: RelationDAO,
  cc: MessagesControllerComponents
) extends AbstractController(cc) with I18nSupport {
  implicit lazy val executionContext = defaultExecutionContext

  /**
    * 組織一覧
    */

  def list = Action.async { implicit request =>
    for {
      organizationSeq <- organizationDao.findAll
      relationSeq     <- relationDao.findAll
    } yield {
      val vv = SiteViewValueOrganizationList(
        layout        = ViewValuePageLayout(id = request.uri),
        organizations = organizationSeq,
        relations     = relationSeq
      )

      Ok(views.html.site.organization.list.Main(vv))
    }
  }

  /**
    * 組織詳細
    */
  def show(id: Organization.Id) = Action.async { implicit request =>

    for {
      relations <- relationDao.getRelations(id) //組織idで　filter
      facilityList <- facilityDao.filterByIds(relations.map(f => f.facilityId))
      organization <- organizationDao.get(id)
    } yield {

      val vv = SiteViewValueOrganizationShow(
        layout        = ViewValuePageLayout(id = request.uri),
        organization  = organization.get,
        facilities    = facilityList
      )

      Ok(views.html.site.organization.show.Main(vv))
    }

  }


  /**
    * 新規作成
   */
  def newOrganization = Action { implicit request =>
    val vv =  ViewValuePageLayout(id = request.uri)
    Ok(views.html.site.organization.new_organization.Main(vv, formForNewOrganization))
  }


  def create = Action { implicit request =>
    formForNewOrganization.bindFromRequest.fold(
      errors => {
        val vv = ViewValuePageLayout(id = request.uri)
        BadRequest(views.html.site.organization.new_organization.Main(vv, errors))
      },
      organization => {
        //for-yield使いたいけどうまくいかないからとりあえず動くやつ
        //asyncにすればいいんだけど,そしたらBadRequestでエラーが出ちゃう

        //for {
        //  _ <- facilityDao.add(facility)
        //} yield {
        organizationDao.add(organization)
        Redirect("/organization/list")
        //}
      }

    )
  }


  /**
    * 編集
    */

  def edit(id: Organization.Id) = Action.async { implicit request =>

    for{
      organization <- organizationDao.get(id)
      relations    <- relationDao.getRelations(id)
      facilities   <- facilityDao.findAll  //選択肢
      //facilities   <- facilityDao.filterByIds(relations.map(f => f.facilityId))
    }yield{
      var vv = SiteViewValueOrganizationEdit(
        layout       = ViewValuePageLayout(id = request.uri),
        organization = organization.get,
        facilities   = facilities,
        relations    = relations
      )

      val formOrganization = formForOrganizationEdit.fill(
        OrganizationEdit(
          Option(organization.get.locationId),
          organization.get.enName,
          organization.get.kanziName,
          organization.get.phoneticName,
          organization.get.address
        )
      )
      Ok(views.html.site.organization.edit.Main(vv, formOrganization, Seq(formForNewRelation)))
    }

  }

  def update(id: Organization.Id) = Action.async { implicit request =>

    formForOrganizationEdit.bindFromRequest.fold(

      errors => {
        for {
          organization <- organizationDao.get(id)
          relations    <- relationDao.getRelations(id)
          //facilities   <- facilityDao.findAll
          facilities   <- facilityDao.filterByIds(relations.map(f => f.facilityId))
        } yield {
          var vv = SiteViewValueOrganizationEdit(
            layout       = ViewValuePageLayout(id = request.uri),
            organization = organization.get,
            facilities   = facilities,
            relations    = relations
          )
          BadRequest(views.html.site.organization.edit.Main(vv, errors, Seq(formForNewRelation)))
        }

      },
      form => {
        for {
          _ <- organizationDao.update(id, form)
        } yield {

          Redirect("/organization/list")
        }
      }

    )

  }

  /**
    * 組織削除
    */
  def delete(id: Organization.Id) = Action.async { implicit request =>
    for {
      _ <- organizationDao.delete(id)
    } yield {
      Redirect("/organization/list")
    }

  }


}