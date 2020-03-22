package jp.covid19_kagawa.covid19information.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.databinding.NewsRepoItemBinding
import jp.covid19_kagawa.covid19information.entity.NewsEntity

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
        holder.binding.root.setOnClickListener { onItemClicked?.invoke(item) }
        holder.binding.executePendingBindings()

//        holder.binding.root.setOnClickListener { onItemClicked?.invoke(item) }
//        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: NewsRepoItemBinding) : RecyclerView.ViewHolder(binding.root)
}