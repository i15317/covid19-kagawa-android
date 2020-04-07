package jp.covid19_kagawa.covid19information2.entity

data class InspectionSummary(
    val date: String,
    val value: String,
    val value_inside: String,
    val value_outside: String,
    val count_inspection: String
)