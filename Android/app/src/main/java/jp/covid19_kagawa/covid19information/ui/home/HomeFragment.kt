package jp.covid19_kagawa.covid19information.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.covid19_kagawa.covid19information.actioncreator.InfectionActionCreator
import jp.covid19_kagawa.covid19information.store.InfectionStore
import jp.covid19_kagawa.covid19information.adapter.InfectionAdapter
import jp.covid19_kagawa.covid19information.adapter.NewsAdapter
import jp.covid19_kagawa.covid19information.databinding.FragmentHomeBinding
import jp.covid19_kagawa.covid19information.observe
import okhttp3.internal.notify
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val store: InfectionStore by viewModel()
    private val actionCreator: InfectionActionCreator by inject()
    private val newsAdapter = NewsAdapter()
    private val infectionAdapter = InfectionAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        //アダプターセット
        binding.infectionList.adapter = infectionAdapter
        binding.newsList.adapter = newsAdapter
        observeState()
        actionCreator.fetchNewsData()
        actionCreator.getInfectData()
        return binding.root
    }

    private fun observeState() {
        store.loadedNewsList.observe(this) {
            it ?: return@observe
            newsAdapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }

        store.loadedInfectionData.observe(this) {
            it ?: return@observe

            infectionAdapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }

    }
}
