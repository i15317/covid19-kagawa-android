package jp.covid19_kagawa.covid19information2.action

import jp.covid19_kagawa.covid19information2.entity.NewsEntity

sealed class NewsAction {
    class ShowLoading(val isLoading: Boolean) : NewsAction()
    class FetchNewsData(val data: List<NewsEntity>) : NewsAction()

}