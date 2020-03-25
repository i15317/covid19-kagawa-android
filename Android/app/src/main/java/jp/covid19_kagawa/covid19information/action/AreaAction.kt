package jp.covid19_kagawa.covid19information.action

import jp.covid19_kagawa.covid19information.room.entity.AreaEntity

sealed class AreaAction {
    class ShowLoading(val isLoading: Boolean) : AreaAction()
    class SelectPrefAreaAction(val areaList: List<AreaEntity>) : AreaAction()
}