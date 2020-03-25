package jp.covid19_kagawa.covid19information.ui.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.actioncreator.PrefectureActionCreator
import jp.covid19_kagawa.covid19information.adapter.PrefAdapter
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.PrefectureStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PrefectureFragment() : Fragment() {
    private val store: PrefectureStore by viewModel()
    private val actionCreator: PrefectureActionCreator by inject()
    private val adapter = PrefAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = layoutInflater.inflate(R.layout.fragment_pref, container, false)
        store.loadedAreaList.observe(this) {
            adapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }
        adapter.onItemClicked = {
            Toast.makeText(context, "Sucess", Toast.LENGTH_SHORT).show()
        }
        return root
    }
}