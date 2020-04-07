package jp.covid19_kagawa.covid19information2.actioncreator

import androidx.transition.TransitionManager
import jp.covid19_kagawa.covid19information2.TransitionData
import jp.covid19_kagawa.covid19information2.action.GuideAction
import jp.covid19_kagawa.covid19information2.data.repository.PreferenceRepository
import jp.covid19_kagawa.covid19information2.flux.ActionCreator
import jp.covid19_kagawa.covid19information2.flux.Dispatcher
import jp.covid19_kagawa.covid19information2.repository.GuideRepository
import jp.covid19_kagawa.covid19information2.room.entity.WebEntity

class GuideActionCreator(
    private val guideRepository: GuideRepository,
    private val prefereceRepository: PreferenceRepository,
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

    //it.prefCode == preferenceRepository.getCurrentPrectureCode()
    fun getWebsiteLinks() = prefereceRepository.getCurrentPrectureCode().also {
        //現状はデータベースにアクセスしない
        //Todo:消せ
        val evilEntity = WebEntity(
            0,
            it.toString(),
            "",
            "",
            "",
            guideRepository.getCurrentWebLink(),
            "",
            false
        )
        dispatch(GuideAction.GetWebLinks(evilEntity))
    }

    fun getCallLinks() = guideRepository.getCurrentCallLink().also {
        dispatch(GuideAction.GetCallLink(it))
    }
}