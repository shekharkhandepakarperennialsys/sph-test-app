package com.sphtechapp.sphtestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sphtechapp.sphtestapp.common.Constants
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import com.sphtechapp.sphtestapp.databinding.FragmentDatastorePagerItemBinding
import com.sphtechapp.sphtestapp.databinding.FragmentRecordDetailBinding
import com.sphtechapp.sphtestapp.databinding.FragmentRecordListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DatastorePagerFragment : Fragment() {

    private val datastorePagerViewModel: DatastoreContract.ViewPagerViewModel by viewModel<DatastorePagerViewModel>()

    private lateinit var _binding: FragmentDatastorePagerItemBinding

    private val binding get() = _binding

    private lateinit var year: String
    private val quarterWiseVolumeData = arrayListOf<DatastoreRecordEntity>()

    companion object {
        fun newInstance(year: String): DatastorePagerFragment {
            val datastorePagerFragment = DatastorePagerFragment()
            val bundle = Bundle()
            bundle.putString(Constants.IntentKeys.POSITION, year)
            datastorePagerFragment.arguments = bundle
            return datastorePagerFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        year = requireArguments().getString(Constants.IntentKeys.POSITION, "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::_binding.isInitialized) {
            _binding = FragmentDatastorePagerItemBinding.inflate(inflater, container, false)
            binding.rvRecordDetailList.layoutManager = LinearLayoutManager(requireContext())
            binding.rvRecordDetailList.adapter = RecordDetailAdapter(quarterWiseVolumeData)
            setupViewModel()
        }
        return binding.root
    }

    private fun setupViewModel() {
        datastorePagerViewModel.getDatastoreEntry().observe(viewLifecycleOwner, {
            quarterWiseVolumeData.clear()
            quarterWiseVolumeData.addAll(it)
            (binding.rvRecordDetailList.adapter as RecordDetailAdapter).notifyDataSetChanged()
        })

        datastorePagerViewModel.searchDatastoreEntry(year)
    }
}