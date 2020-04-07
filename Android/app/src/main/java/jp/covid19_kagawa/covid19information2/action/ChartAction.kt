package jp.covid19_kagawa.covid19information2.action


import jp.covid19_kagawa.covid19information2.entity.InspectionData

sealed class ChartAction {
    class FetchInfectData(val list: List<InspectionData>) : ChartAction()
    class ShowLoading(val isLoading: Boolean) : ChartAction()

}