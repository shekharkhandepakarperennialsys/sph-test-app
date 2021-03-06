package com.sphtechapp.sphtestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sphtechapp.sphtestapp.R
import com.sphtechapp.sphtestapp.common.Constants
import com.sphtechapp.sphtestapp.common.ProgressStatus
import com.sphtechapp.sphtestapp.common.longToast
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import com.sphtechapp.sphtestapp.databinding.FragmentRecordListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecordListFragment : Fragment(), ClickHandler {

    private val recordListViewModel: RecordListViewModel by viewModel()

    private lateinit var _binding: FragmentRecordListBinding

    private val binding get() = _binding

    private val yearData = arrayListOf<String>()
    private val yearWiseVolumeData = arrayListOf<DatastoreRecordEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordListBinding.inflate(inflater, container, false)
        binding.rvRecordList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecordList.adapter = RecordListAdapter(yearWiseVolumeData, this)
        setupViewModel()
        return binding.root
    }

    private fun setupViewModel() {
        recordListViewModel.getRecords().observe(viewLifecycleOwner, {
            yearWiseVolumeData.clear()
            yearData.clear()
            it.forEach { datastoreEntry ->
                val year = datastoreEntry.quarter.substring(0, 4)
                val yearWiseData =
                    yearWiseVolumeData.filter { yearWiseFilter -> yearWiseFilter.year == year }
                if (yearWiseData.isEmpty()) {
                    datastoreEntry.year = year
                    datastoreEntry.total_volume_data = datastoreEntry.volume_of_mobile_data
                    yearWiseVolumeData.add(datastoreEntry)
                } else {
                    yearWiseData[0].total_volume_data =
                        yearWiseData[0].total_volume_data.toBigDecimal().plus(datastoreEntry.volume_of_mobile_data.toBigDecimal()).toString()
                }
            }
            yearWiseVolumeData.forEach { yearWiseVolumeDataEach ->
                yearData.add(yearWiseVolumeDataEach.year)
            }
            (binding.rvRecordList.adapter as RecordListAdapter).notifyDataSetChanged()
        })

        recordListViewModel.getProgressStatus().observe(viewLifecycleOwner, {
            when (it) {
                is ProgressStatus.Loading -> displayLoading()
                is ProgressStatus.Success -> hideLoading()
                is ProgressStatus.Error -> displayError(it.errorMessage)
            }
        })
        recordListViewModel.searchRecords()
    }

    private fun displayLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun displayError(errorMessage: String) {
        hideLoading()
        requireContext().longToast(errorMessage)
    }

    override fun handleClick(view: View, position: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.IntentKeys.POSITION, position)
        bundle.putStringArrayList(Constants.IntentKeys.YEAR_DATA, yearData)
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
    }
}