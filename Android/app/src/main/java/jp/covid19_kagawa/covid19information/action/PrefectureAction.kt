package jp.covid19_kagawa.covid19information.action

import jp.digital_future.cameraxsample.room.entity.PrefectureEntity

sealed class PrefectureAction {
    class ShowLoading(val isLoading: Boolean) : PrefectureAction()

    class SelectPrefAction(val prefList: List<PrefectureEntity>) : PrefectureAction()
}