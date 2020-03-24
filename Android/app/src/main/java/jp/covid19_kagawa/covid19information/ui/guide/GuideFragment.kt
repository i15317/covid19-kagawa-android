package jp.covid19_kagawa.covid19information.ui.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Scene
import androidx.transition.TransitionManager
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.databinding.Scene1Binding
import jp.covid19_kagawa.covid19information.databinding.Scene2Binding
import kotlinx.android.synthetic.main.fragment_guide.view.*

class GuideFragment : Fragment() {

    // We transition between these Scenes

    private lateinit var mView1: Scene1Binding
    private lateinit var mView2: Scene2Binding
    private lateinit var mScene1: Scene
    private lateinit var mScene2: Scene

    /** Transitions take place in this ViewGroup. We retain this for the dynamic transition on scene 4.  */
    private lateinit var mSceneRoot: ViewGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(R.layout.fragment_guide, container, false)!!

        mSceneRoot = view.findViewById<View>(R.id.scene_root) as ViewGroup

        mView1 = Scene1Binding.inflate(inflater, mSceneRoot, false)
        mView2 = Scene2Binding.inflate(inflater, mSceneRoot, false)

        mView1.buttonScene1.setOnClickListener {
            TransitionManager.go(mScene2)
        }
        mView2.buttonScene2.setOnClickListener {
            TransitionManager.go(mScene1)
        }
        // BEGIN_INCLUDE(instantiation_from_view)
// A Scene can be instantiated from a live view hierarchy.
        mScene1 = Scene(
            mSceneRoot,
            mView1.root
        )

        mScene2 = Scene(mSceneRoot, mView2.root)
        TransitionManager.go(mScene1)


        // END_INCLUDE(custom_transition_manager)
        return view
    }


}