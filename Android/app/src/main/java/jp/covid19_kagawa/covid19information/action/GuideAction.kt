package jp.covid19_kagawa.covid19information.action

sealed class GuideAction {
    class ShowLoading(val isLoading: Boolean) : GuideAction()
    class ChangeGuideScene(val title: String) : GuideAction()
}