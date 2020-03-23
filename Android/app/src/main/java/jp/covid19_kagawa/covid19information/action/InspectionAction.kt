package jp.covid19_kagawa.covid19information.action

import jp.covid19_kagawa.covid19information.entity.InfectionSummary
import jp.covid19_kagawa.covid19information.entity.InspectionSummary
import jp.covid19_kagawa.covid19information.entity.NewsEntity

sealed class InspectionAction {
    class FetchInspectionData(val data: InspectionSummary) : InspectionAction()
    class ShowLoading(val isLoading: Boolean) : InspectionAction()

}