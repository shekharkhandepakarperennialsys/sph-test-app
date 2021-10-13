package com.sphtechapp.sphtestapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sphtechapp.sphtestapp.common.Constants
import com.sphtechapp.sphtestapp.common.Constants.IntentKeys.Companion.YEAR_SELECTED
import com.sphtechapp.sphtestapp.databinding.FragmentRecordDetailBinding
import com.sphtechapp.sphtestapp.receiver.YearTrackerReceiver

class RecordDetailFragment : Fragment() {

    private lateinit var _binding: FragmentRecordDetailBinding

    private val binding get() = _binding

    private var position: Int = 0
    private var yearData = arrayListOf<String>()

    private val pageChanged =  object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            sendBroadCast(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordDetailBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = requireArguments().getInt(Constants.IntentKeys.POSITION, 0)
        yearData = requireArguments().getStringArrayList(Constants.IntentKeys.YEAR_DATA) as ArrayList<String>
    }

    private fun init() {
        setupTabs(position)
    }

    private fun setupTabs(positionClicked: Int) {
        binding.viewPager.registerOnPageChangeCallback(pageChanged)
        binding.viewPager.adapter = DatastorePagerAdapter(requireActivity(), yearData)
        binding.viewPager.offscreenPageLimit = 3
        binding.viewPager.setCurrentItem(positionClicked, false)
        binding.tabEntryYear.getTabAt(positionClicked)?.select()

        TabLayoutMediator(binding.tabEntryYear, binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = yearData[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.registerOnPageChangeCallback(pageChanged)
        yearData.clear()
    }

    private fun sendBroadCast(pagePosition: Int) {
        val intent = Intent()
        intent.setClass(requireActivity(), YearTrackerReceiver::class.java)
        intent.putExtra(YEAR_SELECTED, yearData[pagePosition])
        requireActivity().sendBroadcast(intent)
    }
}