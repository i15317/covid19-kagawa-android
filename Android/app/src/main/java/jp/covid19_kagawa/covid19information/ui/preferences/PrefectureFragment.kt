package jp.covid19_kagawa.covid19information.ui.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.actioncreator.PrefectureActionCreator
import jp.covid19_kagawa.covid19information.adapter.PrefAdapter
import jp.covid19_kagawa.covid19information.databinding.FragmentAreaBinding
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.PrefectureStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PrefectureFragment : Fragment() {
    private val store: PrefectureStore by viewModel()
    private val actionCreator: PrefectureActionCreator by inject()
    private val adapter = PrefAdapter()
    private val args: PrefectureFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAreaBinding.inflate(inflater)
        binding.areaList.adapter = adapter
        val root = binding.root
        val view = root.findViewById<RecyclerView>(R.id.pref_list)

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

        actionCreator.getPrefNames(args.content.classCode)
        return root
    }
}