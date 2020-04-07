package jp.covid19_kagawa.covid19information2.data.entity.aomori


data class AomoriData(
    val discharges_summary: DischargesSummary,
    val inspection_persons: InspectionPersons,
    val inspection_status_simple: InspectionStatusSimple,
    val inspection_status_summary: InspectionStatusSummary,
    val lastUpdate: String,
    val main_summary: MainSummary,
    val patients: Patients,
    val patients_summary: PatientsSummary
)

data class DischargesSummary(
    val `data`: List<Data>,
    val date: String
)

data class InspectionPersons(
    val datasets: List<Dataset>,
    val date: String,
    val labels: List<String>
)

data class InspectionStatusSimple(
    val attr: String,
    val children: List<Children>,
    val date: String,
    val value: Int
)

data class InspectionStatusSummary(
    val attr: String,
    val children: List<ChildrenX>,
    val date: String,
    val value: Int
)

data class MainSummary(
    val attr: String,
    val children: List<ChildrenXXX>,
    val value: Int
)

data class Patients(
    val `data`: List<DataX>,
    val date: String
)

data class PatientsSummary(
    val `data`: List<DataXX>,
    val date: String
)

data class Data(
    val 小計: Int,
    val 日付: String
)

data class Dataset(
    val `data`: List<Int>,
    val label: String
)

data class Children(
    val attr: String,
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

data class ChildrenXXX(
    val attr: String,
    val children: List<ChildrenXXXX>,
    val value: Int
)

data class ChildrenXXXX(
    val attr: String,
    val children: List<ChildrenXXXXX>,
    val value: Int
)

data class ChildrenXXXXX(
    val attr: String,
    val value: Int
)

data class DataX(
    val date: String,
    val リリース日: String,
    val 居住地: String,
    val 年代: String,
    val 性別: String,
    val 退院: Any
)

data class DataXX(
    val 小計: Int,
    val 日付: String
)