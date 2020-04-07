package jp.covid19_kagawa.covid19information2.data.entity.aomori

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class AomoriCallData : ArrayList<AomoriCallDataItem>()

@JsonClass(generateAdapter = true)
data class AomoriCallDataItem(
    val 全国地方公共団体コード: String,
    val 受付_年月日: String,
    val 市区町村名: String,
    @Json(name = "相談件数(対応)") val contact: String,
    val 都道府県名: String
)
