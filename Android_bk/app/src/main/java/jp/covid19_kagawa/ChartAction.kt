package jp.covid19_kagawa


import jp.covid19_kagawa.entity.InspectionData

sealed class ChartAction {
    class FetchInfectData(val list: List<InspectionData>) : ChartAction()
    class ShowLoading(val isLoading: Boolean) : ChartAction()

}