package jp.covid19_kagawa.covid19information.actioncreator

import android.content.Context
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.covid19_kagawa.covid19information.action.AreaAction
import jp.covid19_kagawa.covid19information.data.repository.DatabaseRepository
import jp.covid19_kagawa.covid19information.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.room.database.JapanTopDatabase
import jp.covid19_kagawa.covid19information.room.database.PrefectureDatabase
import jp.covid19_kagawa.covid19information.room.entity.PrefectureEntity

class AreaActionCreator(
    private val repository: DatabaseRepository,
    private val preferenceRepository: PreferenceRepository,
    dispatcher: Dispatcher
) : ActionCreator<AreaAction>(dispatcher) {
    private val defaultEntity = PrefectureEntity(0, "002", "東京", "13")

    //アンチパターンな気がするがまあ良し
    //判定はFragmentで行う（システムリソースの責務になるため）
    fun initDatabase(context: Context) = repository.initDatabase(context).also {
        preferenceRepository.updateCurrentPrefecture(defaultEntity)
    }

    fun updateDatabase(context: Context) {
        if (preferenceRepository.checkCurrentVersion()) {
            JapanTopDatabase.getInstance().japanTopDao().evilDeleteAllData()
            PrefectureDatabase.getInstance().prefectureDao().evilDeleteAllData()
            initDatabase(context)
        }
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
            onError = {
                //Timber.e(it)
            }
        )

    fun getCurrentPrefectureName() =
        preferenceRepository.getCurrentPrefectureName().subscribeOn(Schedulers.io()).subscribeBy(
            onSuccess = {
                dispatch(AreaAction.GetCurrentPrefectureNameAction(it))
            },
            onError = {
                //    Timber.e(it)
            }
        )

}