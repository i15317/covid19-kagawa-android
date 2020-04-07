package jp.covid19_kagawa.covid19information2.action

import jp.covid19_kagawa.covid19information2.entity.EntranceData


sealed class EntranceAction {
    class FetchEntranceData(val data: EntranceData) : EntranceAction()
    class ShowLoading(val isLoading: Boolean) : EntranceAction()

}