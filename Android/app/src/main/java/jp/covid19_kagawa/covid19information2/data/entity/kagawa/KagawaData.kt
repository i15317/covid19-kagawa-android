package jp.covid19_kagawa.covid19information2.data.entity.kagawa


data class KagawaData(
    val contacts: Contacts,
    val discharges_summary: DischargesSummary,
    val inspection_persons: InspectionPersons,
    val inspection_status_summary: InspectionStatusSummary,
    val inspections: Inspections,
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

data class InspectionPersons(
    val datasets: List<Dataset>,
    val date: String,
    val labels: List<String>
)

data class InspectionStatusSummary(
    val attr: String,
    val children: List<Children>,
    val date: String,
    val value: Int
)

data class Inspections(
    val `data`: List<DataXX>,
    val date: String
)

data class InspectionsSummary(
    val `data`: DataXXX,
    val date: String,
    val labels: List<String>
)

data class MainSummary(
    val attr: String,
    val children: List<ChildrenXX>,
    val value: Int
)

data class Patients(
    val `data`: List<DataXXXX>,
    val date: String
)

data class PatientsSummary(
    val `data`: List<DataXXXXX>,
    val date: String
)

data class Querents(
    val `data`: List<DataXXXXXX>,
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

data class Dataset(
    val `data`: List<Int>,
    val label: String
)

data class Children(
    val attr: String,
    val children: List<ChildrenX>,
    val value: Int
)

data class ChildrenX(
    val attr: String,
    val value: Int
)

data class DataXX(
    val クルーズ船: String,
    val チャーター便: String,
    val 判明日: String,
    val `小計①`: String,
    val `小計②`: String,
    val 接触者調査: String,
    val 検査検体数: String,
    val 疑い例検査: String,
    val 陰性確認: String,
    val 陰性確認2: String
)

data class DataXXX(
    val その他: List<Int>,
    val 県内: List<Int>
)

data class ChildrenXX(
    val attr: String,
    val children: List<ChildrenXXX>,
    val value: Int
)

data class ChildrenXXX(
    val attr: String,
    val children: List<ChildrenXXXX>,
    val value: Int
)

data class ChildrenXXXX(
    val attr: String,
    val value: Int
)

data class DataXXXX(
    val date: String,
    val リリース日: String,
    val 居住地: String,
    val 年代: String,
    val 性別: String,
    val 曜日: Int,
    val 退院: Any
)

data class DataXXXXX(
    val 小計: Int,
    val 日付: String
)

data class DataXXXXXX(
    val `17-翌9時`: Int,
    val `9-17時`: Int,
    val date: String,
    val short_date: String,
    val w: Int,
    val 小計: Int,
    val 日付: String,
    val 曜日: Int
)