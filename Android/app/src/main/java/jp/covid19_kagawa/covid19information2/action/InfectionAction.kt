package jp.covid19_kagawa.covid19information2.action

import jp.covid19_kagawa.covid19information2.entity.InfectionSummary

sealed class InfectionAction {
    class FetchInfectionData(val data: InfectionSummary) : InfectionAction()
    class ShowLoading(val isLoading: Boolean) : InfectionAction()
    class GetCurrentPrefectureNameAction(val prefName: String) : InfectionAction()

}