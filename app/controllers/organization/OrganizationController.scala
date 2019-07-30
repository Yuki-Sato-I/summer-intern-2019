package controllers.organization

import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, MessagesControllerComponents}
import model.site.organization.SiteViewValueOrganizationList
import model.site.organization.SiteViewValueOrganizationShow

import model.component.util.ViewValuePageLayout

import persistence.organization.model.Organization
import persistence.organization.dao.OrganizationDAO
import persistence.organization.dao.RelationDAO

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





}