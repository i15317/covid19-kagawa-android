package jp.covid19_kagawa.covid19information.repository

import jp.covid19_kagawa.covid19information.GuideSelection

class GuideRepository {

    fun selectTitleMessage(selection: GuideSelection) = selection.selectGuideSceneTitle()


}