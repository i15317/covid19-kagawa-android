package jp.covid19_kagawa.covid19information2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.covid19_kagawa.covid19information2.R
import jp.covid19_kagawa.covid19information2.databinding.PrefRepoItemBinding

import jp.covid19_kagawa.covid19information2.room.entity.PrefectureEntity
import jp.covid19_kagawa.covid19information2.setSafeClickListener

class PrefAdapter : RecyclerView.Adapter<PrefAdapter.ViewHolder>() {
    val items = ArrayList<PrefectureEntity>()
    var onItemClicked: ((item: PrefectureEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.pref_repo_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        // holder.binding.repo = item
        holder.binding.item = item
        holder.binding.root.setSafeClickListener {
            onItemClicked?.invoke(item)
        }
        holder.binding.executePendingBindings()

//        holder.binding.root.setOnClickListener { onItemClicked?.invoke(item) }
//        holder.binding.executePendingBindings()
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(val binding: PrefRepoItemBinding) : RecyclerView.ViewHolder(binding.root)
}