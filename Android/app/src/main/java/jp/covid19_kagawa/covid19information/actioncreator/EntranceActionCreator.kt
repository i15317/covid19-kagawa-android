package jp.covid19_kagawa.covid19information.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.action.EntranceAction
import jp.covid19_kagawa.covid19information.data.mapper.TokyoMapper
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.ChartRepository
import timber.log.Timber

class EntranceActionCreator(
    private val repository: ChartRepository,
    dispatcher: Dispatcher
) : ActionCreator<EntranceAction>(dispatcher) {
    fun fetchEntranceData() =
        repository.getInspectData()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(EntranceAction.ShowLoading(true)) }
            .doFinally { dispatch(EntranceAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(EntranceAction.FetchEntranceData(TokyoMapper.getEntranceData(it)))
                },
                onError = { Timber.e(it) }
            )
}