package jp.covid19_kagawa.covid19information2.data.entity.niigata

data class NiigataData(
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
    val date: Any
)

data class DischargesSummary(
    val `data`: List<Any>,
    val date: Any
)

data class Inspections(
    val `data`: List<Data>,
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
    val `data`: List<DataXXXX>,
    val date: String
)

data class Data(
    val クルーズ船: Int,
    val チャーター便: Int,
    val 判明日: String,
    val 接触者調査: Int,
    val 検査検体数: Int,
    val 疑い例検査: Int,
    val 陰性確認: Int,
    val 陰性確認2: Int
)

data class DataX(
    val その他: List<Int>,
    val 都内: List<Int>
)

data class Children(
    val attr: String,
    val children: List<ChildrenX>,
    val value: Int
)

data class ChildrenX(
    val attr: String,
    val children: List<ChildrenXX>,
    val value: Any
)

data class ChildrenXX(
    val attr: String,
    val value: Any
)

data class DataXX(
    val date: String,
    val リリース日: String,
    val 居住地: String,
    val 年代: String,
    val 性別: String,
    val 曜日: String,
    val 退院: Any
)

data class DataXXX(
    val 小計: Int,
    val 日付: String
)

data class DataXXXX(
    val `17-翌9時`: Int,
    val `9-17時`: Int,
    val date: String,
    val short_date: String,
    val w: Int,
    val 小計: Int,
    val 日付: String,
    val 曜日: String
)