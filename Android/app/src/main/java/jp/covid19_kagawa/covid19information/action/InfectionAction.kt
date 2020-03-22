package jp.covid19_kagawa.covid19information.action

import jp.covid19_kagawa.covid19information.entity.InfectionSummary
import jp.covid19_kagawa.covid19information.entity.NewsEntity

sealed class InfectionAction {
    class FetchInfectionData(val data: InfectionSummary) : InfectionAction()
    class ShowLoading(val isLoading: Boolean) : InfectionAction()
    class FetchNewsData(val data: List<NewsEntity>) : InfectionAction()

}