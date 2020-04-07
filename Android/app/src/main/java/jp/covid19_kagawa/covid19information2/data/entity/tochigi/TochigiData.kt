package jp.covid19_kagawa.covid19information2.data.entity.tochigi

data class TochigiData(
    val contacts: Contacts,
    val inspections_summary: InspectionsSummary,
    val lastUpdate: String,
    val main_summary: MainSummary,
    val patients: Patients,
    val patients_summary: PatientsSummary
)

data class Contacts(
    val `data`: List<List<Any>>,
    val date: String
)

data class InspectionsSummary(
    val `data`: List<List<Any>>,
    val date: String
)

data class MainSummary(
    val attr: String,
    val children: List<Children>,
    val value: Int
)

data class Patients(
    val `data`: List<Data>,
    val date: String
)

data class PatientsSummary(
    val `data`: List<List<Any>>,
    val date: String
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

data class Data(
    val リリース日: String,
    val 居住地: String,
    val 年代: String,
    val 性別: String,
    val 退院: Any
)