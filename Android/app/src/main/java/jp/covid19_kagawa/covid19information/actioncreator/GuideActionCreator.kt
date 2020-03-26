package jp.covid19_kagawa.covid19information.actioncreator

import androidx.transition.TransitionManager
import jp.covid19_kagawa.covid19information.TransitionData
import jp.covid19_kagawa.covid19information.action.GuideAction
import jp.covid19_kagawa.covid19information.flux.ActionCreator
import jp.covid19_kagawa.covid19information.flux.Dispatcher
import jp.covid19_kagawa.covid19information.repository.GuideRepository

class GuideActionCreator(
    private val guideRepository: GuideRepository,
    dispatcher: Dispatcher
) : ActionCreator<GuideAction>(dispatcher) {

    fun changeGuideScene(transitionData: TransitionData) {
        //シーン遷移
        TransitionManager.go(transitionData.scene).also {
            //アクション発火
            guideRepository.selectTitleMessage(transitionData.guideSelection).also { title ->
                dispatch(GuideAction.ChangeGuideScene(title))
            }
        }
    }

}