package jp.covid19_kagawa.covid19information.action

import jp.covid19_kagawa.covid19information.entity.InspectionSummary

sealed class InspectionAction {
    class FetchInspectionData(val data: InspectionSummary) : InspectionAction()
    class ShowLoading(val isLoading: Boolean) : InspectionAction()

}