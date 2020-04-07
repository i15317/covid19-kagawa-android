package jp.covid19_kagawa.covid19information2.action

import jp.covid19_kagawa.covid19information2.room.entity.PrefectureEntity

sealed class PrefectureAction {
    class ShowLoading(val isLoading: Boolean) : PrefectureAction()

    class SelectPrefAction(val prefList: List<PrefectureEntity>) : PrefectureAction()
}