package jp.covid19_kagawa.covid19information.actioncreator

import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.action.ChartAction
import jp.covid19_kagawa.covid19information.repository.ChartRepository
import jp.covid19_kagawa.covid19information.data.mapper.TokyoMapper
import timber.log.Timber

class ChartActionCreator(
    private val repository: ChartRepository,
    dispatcher: Dispatcher
) : ActionCreator<ChartAction>(dispatcher) {
    fun getInfectData(count: Int, range: Float) =
        repository.getInspectData()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(ChartAction.ShowLoading(true)) }
            .doFinally { dispatch(ChartAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(

                        ChartAction.FetchInfectData(
                            TokyoMapper.getInspectionData(
                                it,
                                count,
                                range
                            )
                        )
                    )
                },
                onError = { Timber.e(it) }
            )

}