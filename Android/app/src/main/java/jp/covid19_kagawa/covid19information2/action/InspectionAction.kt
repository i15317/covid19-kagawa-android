package jp.covid19_kagawa.covid19information2.action

import jp.covid19_kagawa.covid19information2.entity.InspectionSummary

sealed class InspectionAction {
    class FetchInspectionData(val data: InspectionSummary) : InspectionAction()
    class ShowLoading(val isLoading: Boolean) : InspectionAction()

}