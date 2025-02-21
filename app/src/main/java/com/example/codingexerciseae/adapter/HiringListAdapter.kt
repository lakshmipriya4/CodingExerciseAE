package com.example.codingexerciseae.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codingexerciseae.databinding.ListHeaderBinding
import com.example.codingexerciseae.databinding.ListItemsBinding
import com.example.codingexerciseae.model.Hiring
import javax.inject.Inject

sealed class ListItem {
    data class Header(val listId: Int) : ListItem()
    data class HiringItem(val hiring: Hiring) : ListItem()
}

class HiringListAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ListItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val view = ListHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(view)
            }
            VIEW_TYPE_ITEM -> {
                val view = ListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HiringViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is ListItem.Header -> (holder as HeaderViewHolder).bind(item)
            is ListItem.HiringItem -> (holder as HiringViewHolder).bind(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ListItem.Header -> VIEW_TYPE_HEADER
            is ListItem.HiringItem -> VIEW_TYPE_ITEM
        }
    }

    fun updateHiringList(list: List<Hiring>) {
        items = list.groupBy { it.listId }
            .flatMap { (listId, hiringList) ->
                listOf(ListItem.Header(listId)) + hiringList.map { ListItem.HiringItem(it) }.sortedBy { it.hiring.name }
            }
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): ListItem? {
        return if (position in items.indices) items[position] else null
    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1
    }
}

class HeaderViewHolder(private val binding: ListHeaderBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(header: ListItem.Header) {
        binding.sectionTitle.text = "List ID: ${header.listId}"
    }
}

class HiringViewHolder(private val binding: ListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ListItem.HiringItem) {
        binding.personId.text = item.hiring.id.toString()
        binding.name.text = item.hiring.name
    }
}