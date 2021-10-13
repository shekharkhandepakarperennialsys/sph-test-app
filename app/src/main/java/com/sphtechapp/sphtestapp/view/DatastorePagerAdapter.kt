package com.sphtechapp.sphtestapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DatastorePagerAdapter(
    fragmentActivity: FragmentActivity,
    private val yearData: ArrayList<String>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment = DatastorePagerFragment.newInstance(yearData[position])

    override fun getItemCount() = yearData.size
}