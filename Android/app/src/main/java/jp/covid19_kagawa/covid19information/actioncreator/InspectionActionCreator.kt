package jp.covid19_kagawa.covid19information.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.action.InspectionAction
import jp.covid19_kagawa.covid19information.data.mapper.TokyoMapper
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.InspectionRepository
import timber.log.Timber

class InspectionActionCreator(
    private val repository: InspectionRepository,
    dispatcher: Dispatcher
) : ActionCreator<InspectionAction>(dispatcher) {
    fun fetchInspectData() =
        repository.getInspectionData()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(InspectionAction.ShowLoading(true)) }
            .doFinally { dispatch(InspectionAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        InspectionAction.FetchInspectionData(
                            TokyoMapper.getInspectionSummaryData(it)
                        )
                    )
                },
                onError = { Timber.e(it) }
            )
}