package jp.covid19_kagawa.covid19information2.action


import jp.covid19_kagawa.covid19information2.entity.InspectionDetailSummary

sealed class InspectionDetailAction {
    class FetchInspectionDetailData(
        val list: List<InspectionDetailSummary>
    ) : InspectionDetailAction()

    class ShowLoading(val isLoading: Boolean) : InspectionDetailAction()

}