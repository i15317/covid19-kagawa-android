package jp.covid19_kagawa.covid19information.actioncreator

import android.content.Context
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.action.AreaAction
import jp.covid19_kagawa.covid19information.data.repository.DatabaseRepository
import jp.covid19_kagawa.covid19information.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import timber.log.Timber

class AreaActionCreator(
    private val repository: DatabaseRepository,
    private val preferenceRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<AreaAction>(dispatcher) {
    //アンチパターンな気がするがまあ良し
    //判定はFragmentで行う（システムリソースの責務になるため）
    fun initDatabase(context: Context) = repository.initDatabase(context).also {
        //何かする
    }

    fun getAreaNames() = repository.getAreaNames()
        .subscribeOn(Schedulers.io())
        .doOnSubscribe { dispatch(AreaAction.ShowLoading(true)) }
        .doFinally { dispatch(AreaAction.ShowLoading(false)) }
        .subscribeBy(
            onSuccess = {
                dispatch(
                    AreaAction.SelectPrefAreaAction(
                        it
                    )
                )
            },
            onError = { Timber.e(it) }
        )

    fun getCurrentPrefectureName() =
        preferenceRepository.getCurrentPrefectureName().subscribeOn(Schedulers.io()).subscribeBy(
            onSuccess = {
                dispatch(AreaAction.GetCurrentPrefectureNameAction(it))
            },
            onError = { Timber.e(it) }
        )

}