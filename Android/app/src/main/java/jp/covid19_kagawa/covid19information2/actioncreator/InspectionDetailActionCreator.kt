package jp.covid19_kagawa.covid19information2.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information2.Prefecture
import jp.covid19_kagawa.covid19information2.action.InspectionDetailAction
import jp.covid19_kagawa.covid19information2.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information2.flux.ActionCreator
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.repository.InspectionDetailRepository
import timber.log.Timber

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
                     Timber.e(it)
                }
            )

}