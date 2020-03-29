package jp.covid19_kagawa.covid19information.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.Prefecture
import jp.covid19_kagawa.covid19information.action.InspectionDetailAction
import jp.covid19_kagawa.covid19information.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.InspectionDetailRepository

class InspectionDetailActionCreator(
    private val repository: InspectionDetailRepository,
    private val prefRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<InspectionDetailAction>(dispatcher) {
    fun getInspectionDetailData() =
        repository.fetchInspectionDetailData(
            Prefecture.values().filter { it.prefCode == prefRepository.getCurrentPrectureCode() }
                .first()
        )
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
                onError = {
                    // Timber.e(it)
                }
            )

}