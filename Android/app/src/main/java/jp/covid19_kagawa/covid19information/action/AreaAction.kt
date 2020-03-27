package jp.covid19_kagawa.covid19information.action

import jp.covid19_kagawa.covid19information.room.entity.AreaEntity
import jp.covid19_kagawa.covid19information.room.entity.WebEntity

sealed class AreaAction {
    class ShowLoading(val isLoading: Boolean) : AreaAction()
    class SelectPrefAreaAction(val areaList: List<AreaEntity>) : AreaAction()
    class GetCurrentPrefectureNameAction(val prefName: String) : AreaAction()
}