package jp.covid19_kagawa.covid19information.actioncreator

import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.action.InspectionDetailAction

import jp.covid19_kagawa.covid19information.data.mapper.TokyoMapper
import jp.covid19_kagawa.covid19information.repository.InspectionRepository
import timber.log.Timber

class InspectionDetailActionCreator(
    private val repository: InspectionRepository,
    dispatcher: Dispatcher
) : ActionCreator<InspectionDetailAction>(dispatcher) {
    fun getInspectionDetailData() =
        repository.getInspectionData()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(InspectionDetailAction.ShowLoading(true)) }
            .doFinally { dispatch(InspectionDetailAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        InspectionDetailAction.FetchInspectionDetailData(
                            TokyoMapper.getInspectionDetailData(
                                it
                            )
                        )
                    )
                },
                onError = { Timber.e(it) }
            )

}