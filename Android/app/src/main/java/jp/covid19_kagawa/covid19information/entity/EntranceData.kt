package jp.covid19_kagawa.covid19information.entity

data class EntranceEntry(
    val date: Long,
    val value: Int
)

data class EntranceData(
    val date: String,
    val entries: List<EntranceEntry>
)