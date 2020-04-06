package jp.covid19_kagawa.covid19information.data.entity.aomori

import com.squareup.moshi.JsonClass

class AomoriContactData : ArrayList<AomoriContactDataItem>()

data class AomoriContactDataItem(
    val 全国地方公共団体コード: String,
    val 受付_年月日: String,
    val 相談件数: String,
    val 都道府県名: String
)