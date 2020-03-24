package jp.covid19_kagawa.covid19information.ui.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Scene
import jp.covid19_kagawa.covid19information.GuideSelection
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.TransitionData
import jp.covid19_kagawa.covid19information.actioncreator.GuideActionCreator
import jp.covid19_kagawa.covid19information.databinding.FragmentGuideBinding
import jp.covid19_kagawa.covid19information.databinding.Scene1Binding
import jp.covid19_kagawa.covid19information.databinding.Scene2Binding
import jp.covid19_kagawa.covid19information.store.GuideStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private enum class SCENE {
    SCENE1,
    SCENE2
}

class GuideFragment : Fragment() {

    // We transition between these Scenes

    private lateinit var mView1: Scene1Binding
    private lateinit var mView2: Scene2Binding
    private lateinit var mScene1: Scene
    private lateinit var mScene2: Scene

    /** Transitions take place in this ViewGroup. We retain this for the dynamic transition on scene 4.  */
    private lateinit var mSceneRoot: ViewGroup
    private val store: GuideStore by viewModel()
    private val actionCreator: GuideActionCreator by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGuideBinding.inflate(inflater, container, false)
        val view: View = binding.root

        //元のビューの作成
        mSceneRoot = view.findViewById<View>(R.id.scene_root) as ViewGroup
        //各シーンのビューのインスタンスを作成
        mView1 = Scene1Binding.inflate(inflater, mSceneRoot, false)
        mView2 = Scene2Binding.inflate(inflater, mSceneRoot, false)

//        //シーンのリソース周りの設定
//        mView1.buttonScene1.setOnClickListener {
//            actionCreator.changeGuideScene(selectScene(SCENE.SCENE2))
//        }
//        mView2.buttonScene2.setOnClickListener {
//            actionCreator.changeGuideScene(selectScene(SCENE.SCENE1))
//        }

        //シーンの登録
        mScene1 = Scene(mSceneRoot, mView1.root)
        mScene2 = Scene(mSceneRoot, mView2.root)

        //デフォルトのシーンに移動
        actionCreator.changeGuideScene(selectScene(SCENE.SCENE1))

        //データバインディングの設定
        binding.lifecycleOwner = viewLifecycleOwner
        binding.item = store
        // END_INCLUDE(custom_transition_manager)
        return view
    }

    private fun selectScene(scene: SCENE): TransitionData =
        when (scene) {
            SCENE.SCENE1 -> TransitionData(this.mScene1, GuideSelection.TOP)
            SCENE.SCENE2 -> TransitionData(this.mScene2, GuideSelection.GUIDE)
        }
}