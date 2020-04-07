package jp.covid19_kagawa.covid19information2.action

import jp.covid19_kagawa.covid19information2.room.entity.AreaEntity

sealed class AreaAction {
    class ShowLoading(val isLoading: Boolean) : AreaAction()
    class SelectPrefAreaAction(val areaList: List<AreaEntity>) : AreaAction()
    class GetCurrentPrefectureNameAction(val prefName: String) : AreaAction()
}