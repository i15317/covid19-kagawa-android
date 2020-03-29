package jp.covid19_kagawa.covid19information.data.entity

class hoge : ArrayList<hogeItem>()

data class hogeItem(
    val contacts: Contacts,
    val inspections_status_summary: InspectionsStatusSummary,
    val inspections_summary: InspectionsSummary,
    val main_summary: MainSummary,
    val patients_summary: PatientsSummary,
    val querents: Querents
)

data class Contacts(
    val `data`: List<Data>,
    val date: String
)

data class InspectionsStatusSummary(
    val `data`: List<DataX>,
    val date: String
)

data class InspectionsSummary(
    val `data`: List<DataXX>,
    val date: String
)

data class MainSummary(
    val `data`: List<DataXXX>,
    val date: String
)

data class PatientsSummary(
    val `data`: List<DataXXXX>,
    val date: String
)

data class Querents(
    val `data`: List<DataXXXXX>,
    val date: String
)

data class Data(
    val count: Int,
    val date: String
)

data class DataX(
    val value: Int,
    val その他: Int,
    val 都内発生: Int
)

data class DataXX(
    val date: String,
    val その他: Int,
    val 県内: Int
)

data class DataXXX(
    val dead: Int,
    val heavy: Int,
    val hit: Int,
    val hospital: Int,
    val light: Int,
    val recover: Int,
    val total: Int
)

data class DataXXXX(
    val count: Int,
    val date: String
)

data class DataXXXXX(
    val count: Int,
    val date: String
)