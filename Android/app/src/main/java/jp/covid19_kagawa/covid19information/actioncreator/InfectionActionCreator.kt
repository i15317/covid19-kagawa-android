package jp.covid19_kagawa.covid19information.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.action.InfectionAction
import jp.covid19_kagawa.covid19information.data.mapper.TokyoMapper
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.InfectionRepository
import timber.log.Timber

class InfectionActionCreator(
    private val repository: InfectionRepository,
    dispatcher: Dispatcher
) : ActionCreator<InfectionAction>(dispatcher) {
    fun getInfectData() =
        repository.getInfectionData()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(InfectionAction.ShowLoading(true)) }
            .doFinally { dispatch(InfectionAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        InfectionAction.FetchInfectionData(
                            TokyoMapper.getInfectionData(it)
                        )
                    )
                },
                onError = { Timber.e(it) }
            )

    fun fetchNewsData() =
        repository.getNewsData().subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(InfectionAction.ShowLoading(true)) }
            .doFinally { dispatch(InfectionAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        InfectionAction.FetchNewsData(
                            TokyoMapper.getNewsData(it.newsItems)
                        )
                    )
                },
                onError = {
                    Timber.e(it)
                }
            )
}