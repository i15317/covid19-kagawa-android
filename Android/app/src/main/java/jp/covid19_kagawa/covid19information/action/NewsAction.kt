package jp.covid19_kagawa.covid19information.action

import jp.covid19_kagawa.covid19information.entity.InfectionSummary
import jp.covid19_kagawa.covid19information.entity.NewsEntity

sealed class NewsAction {
    class ShowLoading(val isLoading: Boolean) : NewsAction()
    class FetchNewsData(val data: List<NewsEntity>) : NewsAction()

}