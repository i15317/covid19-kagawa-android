package jp.covid19_kagawa.covid19information.data.repository

import android.content.SharedPreferences
import int
import io.reactivex.Single
import jp.covid19_kagawa.covid19information.room.entity.PrefectureEntity
import string

class PreferenceRepository(
    private val preferences: SharedPreferences
) {
    companion object {
        const val PREF_CURRENT_PREFECTURE = "current_prefecture"
        const val PREF_CURRENT_PREFECTURE_NAME = "current_prefecture_name"
    }

    private var currentPrefecture by preferences.int(-1, PREF_CURRENT_PREFECTURE)
    private var currentPrefectureName by preferences.string("未設定", PREF_CURRENT_PREFECTURE_NAME)

    fun updateCurrentPrefecture(prefectureEntity: PrefectureEntity) {
        currentPrefecture = prefectureEntity.prefCode.toInt()
        currentPrefectureName = prefectureEntity.prefName
    }

    fun getCurrentPrefectureName(): Single<String> = Single.create { emitter ->
        try {
            emitter.onSuccess(currentPrefectureName)
        } catch (e: Exception) {
            emitter.onError(e)
        }
    }

    fun getCurrentPrectureCode(): Int = currentPrefecture
}
