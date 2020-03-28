package jp.covid19_kagawa.covid19information.data.entity.fukushima

data class FukushimaData(
    val contacts: Contacts,
    val discharges_summary: DischargesSummary,
    val inspections: Inspections,
    val inspections_summary: InspectionsSummary,
    val lastUpdate: String,
    val main_summary: MainSummary,
    val patients: Patients,
    val patients_summary: PatientsSummary,
    val querents: Querents
)

data class Contacts(
    val `data`: List<Any>,
    val date: String
)

data class DischargesSummary(
    val `data`: List<Data>,
    val date: String
)

data class Inspections(
    val `data`: List<Any>,
    val date: String
)

data class InspectionsSummary(
    val `data`: DataX,
    val date: String,
    val labels: List<String>
)

data class MainSummary(
    val attr: String,
    val children: List<Children>,
    val value: Int
)

data class Patients(
    val `data`: List<DataXX>,
    val date: String
)

data class PatientsSummary(
    val `data`: List<DataXXX>,
    val date: String
)

data class Querents(
    val `data`: List<Any>,
    val date: String
)

data class Data(
    val 小計: Int,
    val 日付: String
)

data class DataX(
    val その他: List<Int>,
    val 県内: List<Int>
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

data class DataXX(
    val date: String,
    val リリース日: String,
    val 居住地: String,
    val 年代: String,
    val 性別: String,
    val 曜日: Int,
    val 退院: Any
)

data class DataXXX(
    val 小計: Int,
    val 日付: String
)