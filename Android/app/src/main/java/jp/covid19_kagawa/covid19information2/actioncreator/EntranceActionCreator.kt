package jp.covid19_kagawa.covid19information2.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information2.Prefecture
import jp.covid19_kagawa.covid19information2.action.EntranceAction
import jp.covid19_kagawa.covid19information2.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information2.flux.ActionCreator
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.repository.EntranceRepository
import timber.log.Timber

class EntranceActionCreator(
    private val repository: EntranceRepository,
    private val preferenceRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<EntranceAction>(dispatcher) {
    fun fetchEntranceData() =
        repository.fetchEntranceData(Prefecture.values()
            .filter { it.prefCode == preferenceRepository.getCurrentPrectureCode() }
            .first())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(EntranceAction.ShowLoading(true)) }
            .doFinally { dispatch(EntranceAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(EntranceAction.FetchEntranceData(it))
                },
                onError = {
                    Timber.e(it)
                }
            )
}