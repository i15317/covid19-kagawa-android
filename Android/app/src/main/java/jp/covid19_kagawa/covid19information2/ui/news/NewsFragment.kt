package jp.covid19_kagawa.covid19information2.ui.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import jp.covid19_kagawa.covid19information2.R
import jp.covid19_kagawa.covid19information2.actioncreator.NewsActionCreator
import jp.covid19_kagawa.covid19information2.adapter.NewsAdapter
import jp.covid19_kagawa.covid19information2.databinding.FragmentNewsBinding
import jp.covid19_kagawa.covid19information2.observe
import jp.covid19_kagawa.covid19information2.store.NewsStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class NewsFragment : Fragment() {

    private val store: NewsStore by viewModel()
    private val actionCreator: NewsActionCreator by inject()
    private val newsAdapter = NewsAdapter()
    private lateinit var binding: FragmentNewsBinding
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        //アダプターセット

        binding.newsList.adapter = newsAdapter
        val separateLine = DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
            this.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.divider)!!)
        }

        binding.newsList.addItemDecoration(separateLine)

        newsAdapter.onItemClicked = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.main_link))
            startActivity(intent)
        }
        setHasOptionsMenu(true)
        observeState()
        isLoading = true
        actionCreator.fetchNewsData()
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
        store.loadingState.observe(this) {
            binding.progressBarHome.visibility = if (it) View.VISIBLE else View.GONE
            isLoading = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sync -> {
            actionCreator.fetchNewsData()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

}
