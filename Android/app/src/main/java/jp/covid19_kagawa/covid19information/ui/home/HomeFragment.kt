package jp.covid19_kagawa.covid19information.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.actioncreator.InfectionActionCreator
import jp.covid19_kagawa.covid19information.adapter.InfectionAdapter
import jp.covid19_kagawa.covid19information.adapter.NewsAdapter
import jp.covid19_kagawa.covid19information.databinding.FragmentHomeBinding
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.InfectionStore
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

        newsAdapter.onItemClicked = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.main_link))
            startActivity(intent)
        }
        setHasOptionsMenu(true)
        observeState()
        actionCreator.fetchNewsData()
        actionCreator.getInfectData()
        return binding.root
    }

    private fun observeState() {
        store.loadedNewsList.observe(this) {
            newsAdapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }

        store.loadedInfectionData.observe(this) {

            infectionAdapter.run {
                items.clear()
                items.addAll(it)
                notifyDataSetChanged()
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sync -> {
            actionCreator.getInfectData()
            actionCreator.fetchNewsData()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

}
