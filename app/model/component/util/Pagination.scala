/*
 * This file is part of the Nextbeat services.
 *
 * For the full copyright and license information,
 * please view the LICENSE file that was distributed with this source code.
 */

package model.component.util

// 会場検索
//~~~~~~~~~~
case class ViewValuePagination(
  page:       Int,      // ページ番号
  pageOffset: Int,      // ページオフセット
  pageLimit:  Int,      // ページ表示件数
  pageMax:    Int,      // ページ最大数
  total:      Int,      // 該当件数
  hasNext:    Boolean,  // 次頁フラグ
  hasPrev:    Boolean,  // 前頁フラグ
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

