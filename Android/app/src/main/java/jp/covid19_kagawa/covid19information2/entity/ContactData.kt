package jp.covid19_kagawa.covid19information2.entity

data class ContactEntry(
    val date: Long,
    val value: Int
)

data class ContactData(
    val date: String,
    val entries: List<ContactEntry>
)