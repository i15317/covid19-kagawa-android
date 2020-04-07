package jp.covid19_kagawa.covid19information2.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information2.Prefecture
import jp.covid19_kagawa.covid19information2.action.ChartAction
import jp.covid19_kagawa.covid19information2.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information2.flux.ActionCreator
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.repository.ChartRepository
import timber.log.Timber

class ChartActionCreator(
    private val repository: ChartRepository,
    private val preferenceRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<ChartAction>(dispatcher) {
    fun getInfectData() =
        repository.fetchInspectData(
            Prefecture.values()
                .filter { it.prefCode == preferenceRepository.getCurrentPrectureCode() }
                .first())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(ChartAction.ShowLoading(true)) }
            .doFinally { dispatch(ChartAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        ChartAction.FetchInfectData(
                            it
                        )
                    )
                },
                onError = {
                       Timber.e(it)
                }
            )

}