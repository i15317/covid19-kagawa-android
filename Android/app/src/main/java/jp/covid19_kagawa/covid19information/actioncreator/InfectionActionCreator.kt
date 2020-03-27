package jp.covid19_kagawa.covid19information.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.Prefecture
import jp.covid19_kagawa.covid19information.action.InfectionAction
import jp.covid19_kagawa.covid19information.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.InfectionRepository
import jp.covid19_kagawa.covid19information.repository.NewsRepository
import timber.log.Timber

class InfectionActionCreator(
    private val infectionRepository: InfectionRepository,
    private val newsRepository: NewsRepository,
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
                  //  Timber.e(it)
                }
            )

    fun fetchNewsData() =
        newsRepository.fetchNewsData(Prefecture.values()
            .filter { it.prefCode == preferenceRepository.getCurrentPrectureCode() }
            .first()).subscribeOn(Schedulers.io())
            .doOnSubscribe { dispatch(InfectionAction.ShowLoading(true)) }
            .doFinally { dispatch(InfectionAction.ShowLoading(false)) }
            .subscribeBy(
                onSuccess = {
                    dispatch(
                        InfectionAction.FetchNewsData(it)
                    )
                },
                onError = {
                    //Timber.e(it)
                }
            )
}