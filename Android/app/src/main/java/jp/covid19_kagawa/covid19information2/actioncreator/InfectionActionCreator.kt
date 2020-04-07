package jp.covid19_kagawa.covid19information2.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information2.Prefecture
import jp.covid19_kagawa.covid19information2.action.InfectionAction
import jp.covid19_kagawa.covid19information2.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information2.flux.ActionCreator
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.repository.InfectionRepository
import timber.log.Timber

class InfectionActionCreator(
    private val infectionRepository: InfectionRepository,
    private val preferenceRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<InfectionAction>(dispatcher) {
    fun getInfectData() =
        infectionRepository.fetchInfectionData(Prefecture.values()
            .filter { it.prefCode == preferenceRepository.getCurrentPrectureCode() }
            .first())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(InfectionAction.ShowLoading(true)) }
            .doFinally { dispatch(InfectionAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        InfectionAction.FetchInfectionData(
                            it
                        )
                    )
                },
                onError = {
                      Timber.e(it)
                }
            )

    fun getCurrentPrefectureName() =
        preferenceRepository.getCurrentPrefectureName().subscribeOn(Schedulers.io()).subscribeBy(
            onSuccess = {
                dispatch(InfectionAction.GetCurrentPrefectureNameAction(it))
            },
            onError = {
                //    Timber.e(it)
            }
        )
}