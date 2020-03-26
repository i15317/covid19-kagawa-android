package jp.covid19_kagawa.covid19information.repository

import android.content.Context
import jp.covid19_kagawa.covid19information.Prefecture
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.room.database.JapanTopDatabase
import jp.covid19_kagawa.covid19information.room.database.PrefectureDatabase
import jp.covid19_kagawa.covid19information.room.entity.AreaEntity
import jp.covid19_kagawa.covid19information.room.entity.PrefectureEntity

class DatabaseRepository() {
    private val areaDao by lazy { JapanTopDatabase.getInstance().japanTopDao() }
    private val prefDao by lazy { PrefectureDatabase.getInstance().prefectureDao() }

    fun getAreaNames() = areaDao.getAllItem()
    fun getPrefNames(classCode: String) = prefDao.findByClassCode(classCode)


    fun initDatabase(context: Context): Boolean {
        //地方名
        val areaNames = context.resources.getStringArray(R.array.area_names)
        val classCodes = context.resources.getStringArray(R.array.class_codes)
        //北海道・東北
        val hkDatas = context.resources.getStringArray(R.array.pref_hk)
        val hkDatasValue = context.resources.getStringArray(R.array.pref_hk_value)
        //関東
        val tokyoDatas = context.resources.getStringArray(R.array.pref_tokyo)
        val tokyoValues = context.resources.getStringArray(R.array.pref_tokyo_value)
        //信越・北陸
        val kanazawaDatas = context.resources.getStringArray(R.array.pref_kanazawa)
        val kanazawaValues = context.resources.getStringArray(R.array.pref_kanazawa_value)
        //東海
        val centralDatas = context.resources.getStringArray(R.array.pref_central)
        val centralValues = context.resources.getStringArray(R.array.pref_central_value)
        //関西
        val osakaDatas = context.resources.getStringArray(R.array.pref_osaka)
        val osakaValues = context.resources.getStringArray(R.array.pref_osaka_value)
        //中国・四国地方
        val udonDatas = context.resources.getStringArray(R.array.pref_udon)
        val udonValues = context.resources.getStringArray(R.array.pref_udon_value)
        //九州・沖縄
        val hakataDatas = context.resources.getStringArray(R.array.pref_hakata)
        val hakataValues = context.resources.getStringArray(R.array.pref_hakata_value)

        //地方名の登録
        for (i in areaNames.indices) {
            areaDao.upsert(AreaEntity(0, areaNames[i], classCodes[i]))
        }

        //北海道の登録
        insertPref(classCodes[0], hkDatas, hkDatasValue)
        //関東の登録
        insertPref(classCodes[1], tokyoDatas, tokyoValues)
        //信越の登録
        insertPref(classCodes[2], kanazawaDatas, kanazawaValues)
        //東海の登録
        insertPref(classCodes[3], centralDatas, centralValues)
        //関西
        insertPref(classCodes[4], osakaDatas, osakaValues)
        //中国・四国地方
        insertPref(classCodes[5], udonDatas, udonValues)
        //九州・沖縄
        insertPref(classCodes[6], hakataDatas, hakataValues)
        return true
    }

    fun insertPref(classCode: String, prefArrays: Array<String>, prefValues: Array<String>) {
        //都道府県
        for (i in prefArrays.indices) {
            //消せ
            val test = Prefecture.values().filter { prefValues[i].toInt() == it.prefCode }.first()
            prefDao.upsert(
                PrefectureEntity(
                    0,
                    classCode,
                    prefArrays[i],
                    prefValues[i]
                    //  Prefecture.values().filter { prefValues[i].toInt() == it.prefCode }.first()
                )
            )
        }
    }
}