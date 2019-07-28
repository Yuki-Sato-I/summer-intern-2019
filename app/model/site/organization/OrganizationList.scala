package model.site.organization

import model.component.util.ViewValuePageLayout
import persistence.organization.model.Organization
import persistence.organization.model.Relation

// 表示: 組織一覧
//~~~~~~~~~~~~~~~~~~~~~
//memo ここに中間テーブルのリストを作る
case class SiteViewValueOrganizationList(
  layout:   ViewValuePageLayout,
  organizations: Seq[Organization],
  relations    : Seq[Relation]
)