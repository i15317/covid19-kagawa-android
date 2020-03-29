package jp.covid19_kagawa.covid19information.actioncreator

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.action.PrefectureAction
import jp.covid19_kagawa.covid19information.data.repository.DatabaseRepository
import jp.covid19_kagawa.covid19information.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.room.entity.PrefectureEntity

class PrefectureActionCreator(
    private val repository: DatabaseRepository,
    private val preferenceRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<PrefectureAction>(dispatcher) {

    fun getPrefNames(classCode: String) = repository.getPrefNames(classCode)
        .subscribeOn(Schedulers.io())
        .doOnSubscribe { dispatch(PrefectureAction.ShowLoading(true)) }
        .doFinally { dispatch(PrefectureAction.ShowLoading(false)) }
        .subscribeBy(
            onSuccess = {
                dispatch(PrefectureAction.SelectPrefAction(it))
            },
            onError = {
                // Timber.e(it)
            }
        )

    fun updateCurrentPrefecture(prefecture: PrefectureEntity) =
        preferenceRepository.updateCurrentPrefecture(prefecture)
}