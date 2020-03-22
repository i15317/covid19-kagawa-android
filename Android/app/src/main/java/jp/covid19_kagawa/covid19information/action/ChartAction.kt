package jp.covid19_kagawa.covid19information.action


import jp.covid19_kagawa.covid19information.entity.InspectionData

sealed class ChartAction {
    class FetchInfectData(val list: List<InspectionData>) : ChartAction()
    class ShowLoading(val isLoading: Boolean) : ChartAction()

}