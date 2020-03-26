package jp.covid19_kagawa.covid19information.ui.inspection

import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.actioncreator.InspectionActionCreator
import jp.covid19_kagawa.covid19information.adapter.InfectionAdapter
import jp.covid19_kagawa.covid19information.databinding.FragmentInspectionBinding
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.InspectionStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class InspectionFragment : Fragment() {

    private val store: InspectionStore by viewModel()
    private val actionCreator: InspectionActionCreator by inject()
    private var isLoading = false

    private val infectionAdapter = InfectionAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInspectionBinding.inflate(inflater, container, false)
        //アダプターセット
        binding.inspectionList.adapter = infectionAdapter
        binding.lifecycleOwner = viewLifecycleOwner
        binding.item = store
        setHasOptionsMenu(true)
        observeState()
        isLoading = true

        actionCreator.fetchInspectData()
        return binding.root
    }

    private fun observeState() {
        store.loadedInspectionData.observe(this) {
            it
            infectionAdapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }
        store.loadingState.observe(this) {
            this.view!!.findViewById<ProgressBar>(R.id.progress_bar_inspection).visibility =
                if (it) View.VISIBLE else View.GONE
            isLoading = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sync -> {
            actionCreator.fetchInspectData()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

}
