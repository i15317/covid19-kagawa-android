package jp.covid19_kagawa.covid19information.store

import jp.covid19_kagawa.covid19information.action.NewsAction
import jp.covid19_kagawa.covid19information.entity.NewsEntity
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.flux.Store
import jp.covid19_kagawa.covid19information.flux.StoreLiveData
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber


class NewsStore(dispatcher: Dispatcher) : Store(dispatcher) {
    companion object {
        private const val INITIAL_PAGE = 1
        private const val MAIN_TITLE = "累計患者数"
        private const val INFECTED_TITLE = "陽性患者数"
        private const val HOSPITAL_TITLE = "入院患者数"
        private const val LIGHT_TITLE = "軽症・中等症"
        private const val HEAVY_TITLE = "重症"
        private const val RECOVERED_TITLE = "退院"
        private const val DIED_TITLE = "死亡"
        private const val SUB_TITLE = "（累計）"
        private const val SUB_APPEND = "人"
    }

    var canFetchMore = false
        private set

    var pageNum = INITIAL_PAGE
        private set

    private val loadingNewsList = mutableListOf<NewsEntity>()

    val loadingState = StoreLiveData<Boolean>()
    val loadedNewsList = StoreLiveData<List<NewsEntity>>()

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun on(action: NewsAction) {
        when (action) {

            is NewsAction.ShowLoading -> {
                Timber.tag(this::class.java.simpleName).d("action = ${action.isLoading}")
                loadingState.postValue(action.isLoading)
            }

            is NewsAction.FetchNewsData -> {
                canFetchMore = action.data.isNotEmpty()
                pageNum++
                loadingNewsList.clear()
                loadingNewsList.addAll(action.data)
                loadedNewsList.postValue(loadingNewsList)
            }
        }
    }

}