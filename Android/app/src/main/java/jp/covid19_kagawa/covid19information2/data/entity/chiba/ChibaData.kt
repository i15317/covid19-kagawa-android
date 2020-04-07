package jp.covid19_kagawa.covid19information2.data.entity.chiba


data class ChibaData(
    val better_patients_summary: BetterPatientsSummary,
    val contacts: Contacts,
    val discharges: Discharges,
    val discharges_summary: DischargesSummary,
    val inspections: Inspections,
    val inspections_summary: InspectionsSummary,
    val lastUpdate: String,
    val main_summary: MainSummary,
    val patients: Patients,
    val patients_summary: PatientsSummary,
    val querents: Querents
)

data class BetterPatientsSummary(
    val date: String
)

data class Contacts(
    val `data`: List<DataX>,
    val date: String
)

data class Discharges(
    val `data`: List<DataXX>,
    val date: String
)

data class DischargesSummary(
    val `data`: List<DataXXX>,
    val date: String
)

data class Inspections(
    val `data`: List<DataXXXX>,
    val date: String
)

data class InspectionsSummary(
    val `data`: DataXXXXX,
    val date: String,
    val labels: List<String>
)

data class MainSummary(
    val attr: String,
    val children: List<Children>,
    val value: Int
)

data class Patients(
    val `data`: List<DataXXXXXX>,
    val date: String
)

data class PatientsSummary(
    val `data`: List<DataXXXXXXX>,
    val date: String
)

data class Querents(
    val `data`: List<DataXXXXXXXX>,
    val date: String
)

data class DataX(
    val `13-17時`: Int,
    val `17-21時`: Int,
    val `9-13時`: Int,
    val date: String,
    val short_date: String,
    val w: Int,
    val 小計: Int,
    val 日付: String,
    val 曜日: String
)

data class DataXX(
    val date: String,
    val short_date: String,
    val w: Int,
    val リリース日: String,
    val 備考: Any,
    val 居住地: String,
    val 年代: String,
    val 性別: String,
    val 曜日: String,
    val 退院: String
)

data class DataXXX(
    val 小計: Int,
    val 日付: String
)

data class DataXXXX(
    val クルーズ船: Int,
    val チャーター便: Int,
    val 判明日: String,
    val 接触者調査: Int,
    val 検査検体数: Int,
    val 疑い例検査: Int,
    val 陰性確認: Int,
    val 陰性確認2: Int
)

data class DataXXXXX(
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
    val value: Int
)

data class ChildrenXX(
    val attr: String,
    val value: Int
)

data class DataXXXXXX(
    val date: String,
    val short_date: String,
    val w: Int,
    val リリース日: String,
    val 備考: Any,
    val 居住地: String,
    val 年代: String,
    val 性別: String,
    val 曜日: String,
    val 退院: String
)

data class DataXXXXXXX(
    val 小計: Int,
    val 日付: String
)

data class DataXXXXXXXX(
    val `17-翌9時`: Int,
    val `9-17時`: Int,
    val date: String,
    val short_date: String,
    val w: Int,
    val 小計: Int,
    val 日付: String,
    val 曜日: String
)