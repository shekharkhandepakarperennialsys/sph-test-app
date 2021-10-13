package com.sphtechapp.sphtestapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import com.sphtechapp.sphtestapp.databinding.ItemRecordDetailBinding

class RecordDetailAdapter(
    private val quarterWiseVolumeData: ArrayList<DatastoreRecordEntity>) : RecyclerView.Adapter<RecordDetailAdapter.RecordListHolder>() {

    inner class RecordListHolder(private val binding: ItemRecordDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recordEntity: DatastoreRecordEntity) {
            binding.recordEntity = recordEntity
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordListHolder {
        val itemBinding: ItemRecordDetailBinding =
            ItemRecordDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordListHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecordListHolder, position: Int) {
        holder.bind(quarterWiseVolumeData[position])
    }

    override fun getItemCount(): Int {
        return quarterWiseVolumeData.size
    }
}