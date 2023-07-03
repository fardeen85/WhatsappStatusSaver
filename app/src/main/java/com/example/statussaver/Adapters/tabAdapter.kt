package com.example.statussaver.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.statussaver.Fragments.FragmentSaved
import com.example.statussaver.Fragments.FragmentSettings
import com.example.statussaver.Fragments.Images_fragment
import com.example.statussaver.Fragments.Videos_Fragment

class tabAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 4;
    }

    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return Images_fragment()
        } else if (position == 1) {

            return Videos_Fragment();

        } else if (position == 2) {

            return FragmentSaved()
        } else if (position == 3) {

            return FragmentSettings()
        } else {
            return Images_fragment()

        }
    }

    override fun getPageTitle(position: Int): CharSequence? {

        if (position == 0) {

            return "Images"
        }

        if (position == 1) {

            return "Videos"
        }

        if (position == 2) {

            return "Saved"
        }

        if (position == 3) {

            return "Settings"
        } else {

            return ""
        }

        return ""
    }





}