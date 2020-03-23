package jp.covid19_kagawa.covid19information.entity

data class ContactEntry(
    val date: Long,
    val value: Int
)

data class ContactData(
    val date: String,
    val entries: List<ContactEntry>
)