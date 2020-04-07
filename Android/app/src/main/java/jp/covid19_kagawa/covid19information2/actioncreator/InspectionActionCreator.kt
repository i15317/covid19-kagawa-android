package jp.covid19_kagawa.covid19information2.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information2.Prefecture
import jp.covid19_kagawa.covid19information2.action.InspectionAction
import jp.covid19_kagawa.covid19information2.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information2.flux.ActionCreator
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.repository.InspectionRepository
import timber.log.Timber

class InspectionActionCreator(
    private val repository: InspectionRepository,
    private val preferenceRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<InspectionAction>(dispatcher) {
    fun fetchInspectData() =
        repository.fetchInspectionData(Prefecture.values()
            .filter { it.prefCode == preferenceRepository.getCurrentPrectureCode() }
            .first())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(InspectionAction.ShowLoading(true)) }
            .doFinally { dispatch(InspectionAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        InspectionAction.FetchInspectionData(
                            it
                        )
                    )
                },
                onError = {
                      Timber.e(it)
                }
            )
}