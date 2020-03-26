package jp.covid19_kagawa.covid19information.entity

data class NewsEntity(
    val date: String,
    val sub_title: String,
    val main_title: String,
    val main_link: String,
    val option: String
)