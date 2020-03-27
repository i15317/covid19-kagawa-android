package jp.covid19_kagawa.covid19information.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.Prefecture
import jp.covid19_kagawa.covid19information.action.NewsAction
import jp.covid19_kagawa.covid19information.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.NewsRepository

class NewsActionCreator(
    private val newsRepository: NewsRepository,
    private val preferenceRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<NewsAction>(dispatcher) {

    fun fetchNewsData() =
        newsRepository.fetchNewsData(Prefecture.values()
            .filter { it.prefCode == preferenceRepository.getCurrentPrectureCode() }
            .first()).subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(NewsAction.ShowLoading(true)) }
            .doFinally { dispatch(NewsAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        NewsAction.FetchNewsData(it)
                    )
                },
                onError = {
                    //Timber.e(it)
                }
            )
}