package jp.covid19_kagawa.covid19information

import androidx.transition.Scene

data class TransitionData(
    val scene: Scene,
    val guideSelection: GuideSelection
)