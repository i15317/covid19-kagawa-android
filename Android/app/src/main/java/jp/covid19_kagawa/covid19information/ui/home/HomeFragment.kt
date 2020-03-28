package jp.covid19_kagawa.covid19information.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.actioncreator.InfectionActionCreator
import jp.covid19_kagawa.covid19information.adapter.InfectionAdapter
import jp.covid19_kagawa.covid19information.databinding.FragmentHomeBinding
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.InfectionStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val store: InfectionStore by viewModel()
    private val actionCreator: InfectionActionCreator by inject()

    private val infectionAdapter = InfectionAdapter()
    private lateinit var binding: FragmentHomeBinding
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //アダプターセット
        binding.infectionList.adapter = infectionAdapter

        setHasOptionsMenu(true)
        observeState()
        isLoading = true

        actionCreator.getInfectData()
        actionCreator.getCurrentPrefectureName()
        return binding.root
    }

    private fun observeState() {
        store.loadingState.observe(this) {
            binding.progressBarHome.visibility = if (it) View.VISIBLE else View.GONE
            isLoading = it
        }
        store.loadedInfectionData.observe(this) {
            infectionAdapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }

        store.currentPrefectureName.observe(this) {
            binding.textHome.text = it
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sync -> {
            actionCreator.getInfectData()
            actionCreator.getCurrentPrefectureName()

            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

}
