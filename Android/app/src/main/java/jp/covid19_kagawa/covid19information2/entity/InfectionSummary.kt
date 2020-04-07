package jp.covid19_kagawa.covid19information2.entity

data class InfectionSummary(
    val date: Long,
    val inspection_num: Int,
    val infect_num: Int,
    val infect_hospital: Int,
    val infect_light: Int,
    val infect_heavy: Int,
    val infect_died: Int,
    val inafect_recover: Int
)

