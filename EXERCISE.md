# 演習課題

## 1

~~サンプルアプリではFacilityを見ることしか出来ません。
Facilityを編集するページを作成して、既存のFacilityを変更できるようにしてください。~~

## 2

~~既存のFacilityを変更できるようになりましたが、まだ新しいFacilityを追加したり削除することが出来ません
Facilityを追加・削除する機能を作成してください~~

## 3

~~Facilityに対して、新しくOrganizationを追加したいです。  
まずは、Organizationモデルを作成してください。Organizationは最低限下記の情報を持ちます。~~
 - ~~名前(漢字、ふりがな、英語名)を持つ~~
 - ~~住所を持つ。住所は番地・建物名まで含み、なおかつLocationと紐づく~~
 - ~~複数のFacilityと紐づく~~

~~必要に応じてOrganization以外にモデルを作成して良いです~~

## 4

~~3で追加したモデルを使ってDBアクセスできるようにしたいです。  
全件取得、一件取得、編集、追加、削除ができるようにDaoを作成してください~~

## 5

~~4の機能を使って実際の画面を作成したいです  
Organizationの一覧・詳細・新規作成・編集・削除ページを作成してください。~~

## 6

~~5に対して下記の機能を追加してください~~

 - ~~一覧ページでは紐づくFacilityの数~~  
 - ~~詳細ページでは紐づくFacilityの一覧~~  
 - ~~編集ページでは紐づくFacilitynの追加・削除~~

## 7

~~FacilityとOrganizationの一覧ページをページングするようにしてください。
1ページには10件ずつ表示するようにして複数ページに分割し、前後のページへのリンクを貼ってください~~

## 8

何か自分で新しい機能を考え、作成してください。

- 削除時の確認アラート
- FacilityやOrganizationを削除した時,関連性も削除する
