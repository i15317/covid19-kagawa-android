package jp.covid19_kagawa.covid19information.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.Prefecture
import jp.covid19_kagawa.covid19information.action.InspectionDetailAction
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.InspectionDetailRepository
import timber.log.Timber

class InspectionDetailActionCreator(
    private val repository: InspectionDetailRepository,
    dispatcher: Dispatcher
) : ActionCreator<InspectionDetailAction>(dispatcher) {
    fun getInspectionDetailData() =
        repository.fetchInspectionDetailData(Prefecture.TOKYO)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(InspectionDetailAction.ShowLoading(true)) }
            .doFinally { dispatch(InspectionDetailAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        InspectionDetailAction.FetchInspectionDetailData(
                            it
                        )
                    )
                },
                onError = { Timber.e(it) }
            )

}