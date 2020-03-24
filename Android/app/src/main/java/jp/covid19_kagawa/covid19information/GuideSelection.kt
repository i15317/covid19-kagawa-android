package jp.covid19_kagawa.covid19information

enum class GuideSelection {
    TOP {
        override fun selectGuideSceneTitle(): String = "新型コロナウイルス対策について"
    },
    SCENE2 {
        override fun selectGuideSceneTitle(): String = "一般の方"
    },
    SCENE3 {
        override fun selectGuideSceneTitle(): String = "高齢、妊婦の方や既往症をお持ちの方"
    },
    SCENE4 {
        override fun selectGuideSceneTitle(): String = "コロナウイルス感染に不安をお持ちの方"
    };

    abstract fun selectGuideSceneTitle(): String
}