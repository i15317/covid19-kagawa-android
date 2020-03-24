package jp.covid19_kagawa.covid19information

enum class GuideSelection {
    TOP {
        override fun selectGuideSceneTitle(): String = "新型コロナウイルス対策について"
    },
    GUIDE {
        override fun selectGuideSceneTitle(): String = "テスト"
    };

    abstract fun selectGuideSceneTitle(): String
}