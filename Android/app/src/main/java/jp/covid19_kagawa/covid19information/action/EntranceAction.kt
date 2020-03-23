package jp.covid19_kagawa.covid19information.action

import jp.covid19_kagawa.covid19information.entity.EntranceData


sealed class EntranceAction {
    class FetchEntranceData(val data: EntranceData) : EntranceAction()
    class ShowLoading(val isLoading: Boolean) : EntranceAction()

}