package jp.covid19_kagawa.covid19information2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import jp.covid19_kagawa.covid19information2.R
import jp.covid19_kagawa.covid19information2.databinding.InfectionRepoItemBinding
import jp.covid19_kagawa.covid19information2.entity.SummaryEntity

class InfectionAdapter : RecyclerView.Adapter<InfectionAdapter.ViewHolder>() {
    val items = ArrayList<SummaryEntity>()
    var onItemClicked: ((item: SummaryEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.infection_repo_item, parent, false
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

    inner class ViewHolder(val binding: InfectionRepoItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}