package jp.covid19_kagawa.covid19information.ui.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import jp.covid19_kagawa.covid19information.actioncreator.AreaActionCreator
import jp.covid19_kagawa.covid19information.adapter.AreaAdapter
import jp.covid19_kagawa.covid19information.databinding.FragmentAreaBinding
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.AreaStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AreaFragment : Fragment() {
    private val store: AreaStore by viewModel()
    private val actionCreator: AreaActionCreator by inject()
    private val adapter = AreaAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAreaBinding.inflate(inflater)
        val root = binding.root

        store.loadedAreaList.observe(this) {
            adapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }
        binding.item = store
        binding.lifecycleOwner = viewLifecycleOwner
        binding.areaList.adapter = adapter
        actionCreator.getAreaNames()
        actionCreator.getCurrentPrefectureName()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter.onItemClicked = {
            findNavController().navigate(
                AreaFragmentDirections.actionAreaToPref(
                    it
                )
            )
        }
    }

}