package model.site.organization

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

//pagenationのクラス既に存在してたのをあとで見つけちゃった.
//けど自分でつくったものでやっちゃったから自分のものを使う
case class PaginatedResult[T](
  totalCount:      Int,
  pageCount:       Int,
  currentPage:     Int,
  entities:        List[T],
  hasNextPage:     Boolean,
  hasPreviousPage: Boolean
)