package jp.covid19_kagawa

import jp.covid19_kagawa.flux.ActionCreator
import jp.covid19_kagawa.flux.Dispatcher
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.data.mapper.InspectionMapper
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
                            InspectionMapper.getInspectionData(
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