package jp.covid19_kagawa.covid19information.action

import jp.covid19_kagawa.covid19information.room.entity.WebEntity

sealed class GuideAction {
    class ShowLoading(val isLoading: Boolean) : GuideAction()
    class ChangeGuideScene(val title: String) : GuideAction()
    class GetWebLinks(val webEntity: WebEntity) : GuideAction()
    class GetCallLink(val callLink: String) : GuideAction()

}