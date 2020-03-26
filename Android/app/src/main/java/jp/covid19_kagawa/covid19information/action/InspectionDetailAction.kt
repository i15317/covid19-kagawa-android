package jp.covid19_kagawa.covid19information.action


import jp.covid19_kagawa.covid19information.entity.InspectionDetailSummary

sealed class InspectionDetailAction {
    class FetchInspectionDetailData(
        val list: List<InspectionDetailSummary>
    ) : InspectionDetailAction()

    class ShowLoading(val isLoading: Boolean) : InspectionDetailAction()

}