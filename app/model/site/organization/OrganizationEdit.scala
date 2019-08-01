package model.site.organization

import model.component.util.ViewValuePageLayout
import persistence.organization.model.Organization
import persistence.facility.model.Facility
import persistence.organization.model.Relation

// 表示: 組織一覧
//~~~~~~~~~~~~~~~~~~~~~
//memo ここに中間テーブルのリストを作る
case class SiteViewValueOrganizationEdit(
  layout:       ViewValuePageLayout,
  organization: Organization,
  facilities:   Seq[Facility],
  relations:    Seq[Relation]
)