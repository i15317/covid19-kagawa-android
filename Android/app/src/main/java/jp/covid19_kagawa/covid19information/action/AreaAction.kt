package jp.covid19_kagawa.covid19information.action

import jp.digital_future.cameraxsample.room.entity.AreaEntity

sealed class AreaAction {
    class ShowLoading(val isLoading: Boolean) : AreaAction()
    class SelectPrefAreaAction(val areaList: List<AreaEntity>) : AreaAction()
}