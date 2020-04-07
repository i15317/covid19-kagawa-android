package jp.covid19_kagawa.covid19information2.data.entity.gumma

data class GummaData(
    val contacts: Contacts,
    val discharges_summary: DischargesSummary,
    val inspections_summary: InspectionsSummary,
    val lastUpdate: String,
    val main_summary: MainSummary,
    val patients: Patients,
    val patients_summary: PatientsSummary,
    val querents: Querents
)

data class Contacts(
    val `data`: List<Data>,
    val date: String
)

data class DischargesSummary(
    val `data`: List<DataX>,
    val date: String
)

data class InspectionsSummary(
    val `data`: List<DataXX>,
    val date: String
)

data class MainSummary(
    val attr: String,
    val children: List<Children>,
    val value: Int
)

data class Patients(
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
    val `13-17時`: Int,
    val `17-21時`: Int,
    val `9-13時`: Int,
    val date: String,
    val short_date: String,
    val w: Int,
    val 小計: Int,
    val 日付: String,
    val 曜日: Int
)

data class DataX(
    val 小計: Int,
    val 日付: String
)

data class DataXX(
    val 小計: Int,
    val 日付: String
)

data class Children(
    val attr: String,
    val children: List<ChildrenX>,
    val value: Int
)

data class ChildrenX(
    val attr: String,
    val children: List<ChildrenXX>,
    val value: Int
)

data class ChildrenXX(
    val attr: String,
    val value: Int
)

data class DataXXX(
    val date: String,
    val リリース日: String,
    val 居住地: String,
    val 年代: String,
    val 性別: String,
    val 退院: String
)

data class DataXXXX(
    val 小計: Int,
    val 日付: String
)

data class DataXXXXX(
    val `17-翌9時`: Int,
    val `9-17時`: Int,
    val date: String,
    val short_date: String,
    val w: Int,
    val 小計: Int,
    val 日付: String,
    val 曜日: Int
)