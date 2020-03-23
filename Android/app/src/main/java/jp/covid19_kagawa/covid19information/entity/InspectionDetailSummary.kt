package jp.covid19_kagawa.covid19information.entity

data class InspectionDetailSummary(
    val date: Long,
    val inside: Int,
    val outside: Int
)