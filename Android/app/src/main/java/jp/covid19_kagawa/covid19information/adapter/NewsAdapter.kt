package jp.covid19_kagawa.covid19information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.databinding.NewsRepoItemBinding
import jp.covid19_kagawa.covid19information.entity.NewsEntity
import jp.covid19_kagawa.covid19information.setSafeClickListener
import kotlinx.android.synthetic.main.news_repo_item.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    val items = ArrayList<NewsEntity>()
    var onItemClicked: ((item: NewsEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.news_repo_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        // holder.binding.repo = item
        holder.binding.item = item
        holder.binding.root.news_main_num.setSafeClickListener {
            onItemClicked?.invoke(item)
        }
        holder.binding.executePendingBindings()

//        holder.binding.root.setOnClickListener { onItemClicked?.invoke(item) }
//        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: NewsRepoItemBinding) : RecyclerView.ViewHolder(binding.root)
}