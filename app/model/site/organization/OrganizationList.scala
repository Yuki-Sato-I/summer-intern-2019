package model.site.organization

import model.component.util.PaginatedResult
import model.component.util.ViewValuePageLayout
import persistence.organization.model.Organization
import persistence.organization.model.Relation
import persistence.organization.dao.OrganizationDAO

// 表示: 組織一覧
//~~~~~~~~~~~~~~~~~~~~~
case class SiteViewValueOrganizationList(
  layout:        ViewValuePageLayout,
  organizations: PaginatedResult[Organization],
  relations    : Seq[Relation]
)
