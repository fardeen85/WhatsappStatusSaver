package com.example.statussaver.Fragments

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.appcompat.app.AppCompatDelegate
import com.example.statussaver.R
import com.example.statussaver.databinding.FragmentSettingsBinding


class FragmentSettings : Fragment() {


    lateinit var fragmentSettingsBinding:FragmentSettingsBinding
    var isnightmodeon = false;
    lateinit var prefs:SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentSettingsBinding = FragmentSettingsBinding.inflate(layoutInflater)
        prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        fragmentSettingsBinding.switchtheme.isChecked = prefs.getBoolean("isdark",false)
        isnightmodeon = prefs.getBoolean("isdark",false)

        if (isnightmodeon){

            fragmentSettingsBinding.switchtheme.setText("Turn off Dark")
        }



        fragmentSettingsBinding.switchtheme.setOnCheckedChangeListener(object :OnCheckedChangeListener,
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                TODO("Not yet implemented")
            }

            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if (isChecked){

                    Log.d("Tag","switch false")

                    if (!isnightmodeon) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        fragmentSettingsBinding.switchtheme.setText("Switch to Light")
                        isnightmodeon = true
                        savedPreference(true)
                        requireActivity().recreate()

                        fragmentSettingsBinding.switchtheme.visibility = View.GONE
                        fragmentSettingsBinding.progress.visibility = View.VISIBLE

                    }





                }
                else{

                    Log.d("Tag","switch false")

                    if (isnightmodeon) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        fragmentSettingsBinding.switchtheme.setText("Switch to Dark")
                        isnightmodeon = false
                        savedPreference(false)
                        requireActivity().recreate()
                        fragmentSettingsBinding.switchtheme.visibility = View.GONE
                        fragmentSettingsBinding.progress.visibility = View.VISIBLE

                    }

                }
            }




        })
        return fragmentSettingsBinding.root
    }

    fun savedPreference(value:Boolean){


        prefs= PreferenceManager.getDefaultSharedPreferences(requireContext())
        prefs.edit().putBoolean("isdark",value).commit();

    }


}