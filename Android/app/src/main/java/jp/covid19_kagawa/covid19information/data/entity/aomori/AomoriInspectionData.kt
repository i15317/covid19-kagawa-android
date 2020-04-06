package jp.covid19_kagawa.covid19information.data.entity.aomori

import com.squareup.moshi.JsonClass

class AomoriInspectionData : ArrayList<AomoriInspectionDataItem>()

data class AomoriInspectionDataItem(
    val 実施数: String,
    val 検査日時: String,
    val 陰性数: String,
    val 陽性数: String
)