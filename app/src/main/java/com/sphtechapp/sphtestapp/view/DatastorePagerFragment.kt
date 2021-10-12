package com.sphtechapp.sphtestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sphtechapp.sphtestapp.common.Constants
import com.sphtechapp.sphtestapp.databinding.FragmentDatastorePagerItemBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DatastorePagerFragment : Fragment() {

    private val datastorePagerViewModel: DatastoreContract.ViewPagerViewModel by viewModel<DatastorePagerViewModel>()

    private var _binding: FragmentDatastorePagerItemBinding? = null

    private val binding get() = _binding!!

    private lateinit var year: String

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
        _binding = FragmentDatastorePagerItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textviewSecond.text = year
        setupViewModel()
    }

    private fun setupViewModel() {
        datastorePagerViewModel.getDatastoreEntry().observe(viewLifecycleOwner, {

        })

        datastorePagerViewModel.searchDatastoreEntry(year)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}