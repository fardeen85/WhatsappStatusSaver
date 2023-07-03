package com.example.statussaver.Fragments

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.statussaver.Adapters.savedAdapter
import com.example.statussaver.R
import com.example.statussaver.Viewmodels.StatusViewModel
import com.example.statussaver.databinding.FragmentSavedBinding
import java.io.File


class FragmentSaved : Fragment() {

    lateinit var fragmentSaved: FragmentSavedBinding
    lateinit var savedAdapter: savedAdapter
    lateinit var viewModel: StatusViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentSaved = FragmentSavedBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(StatusViewModel::class.java)
        var filesdata = viewModel.getListSavedFiles(File( Environment.getExternalStorageDirectory().toString()+"/SavedStatus"))
        savedAdapter = savedAdapter(filesdata!!,requireContext())
        fragmentSaved.savedrecycler.adapter = savedAdapter



        return fragmentSaved.root
    }


}