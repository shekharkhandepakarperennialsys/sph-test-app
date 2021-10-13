package com.sphtechapp.sphtestapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import com.sphtechapp.sphtestapp.databinding.ItemRecordListBinding

class RecordListAdapter(
    private val yearWiseVolumeData: ArrayList<DatastoreRecordEntity>,
    val clickHandler: ClickHandler
) : RecyclerView.Adapter<RecordListAdapter.RecordListHolder>() {

    inner class RecordListHolder(private val binding: ItemRecordListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recordEntity: DatastoreRecordEntity, position: Int) {
            binding.recordEntity = recordEntity
            binding.listener = clickHandler
            binding.position = position
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordListHolder {
        val itemBinding: ItemRecordListBinding =
            ItemRecordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordListHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecordListHolder, position: Int) {
        holder.bind(yearWiseVolumeData[position], position)
    }

    override fun getItemCount(): Int {
        return yearWiseVolumeData.size
    }
}

interface ClickHandler {
    fun handleClick(view: View, position: Int)
}