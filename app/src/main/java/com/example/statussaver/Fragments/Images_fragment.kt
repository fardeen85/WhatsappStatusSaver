package com.example.statussaver.Fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.statussaver.Adapters.RecyclerAdapter
import com.example.statussaver.Viewmodels.StatusViewModel
import com.example.statussaver.databinding.FragmentImagesFragmentBinding
import java.io.File
import kotlin.concurrent.fixedRateTimer


class Images_fragment : Fragment() {

    lateinit var imgfragmentbinding : FragmentImagesFragmentBinding;
    lateinit var statusmodel : StatusViewModel
    lateinit var recyclerAdapter: RecyclerAdapter
    val WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        imgfragmentbinding = FragmentImagesFragmentBinding.inflate(inflater);

        statusmodel = ViewModelProvider(this).get(StatusViewModel::class.java)




        var fileslist = statusmodel.getListFiles(File(Environment.getExternalStorageDirectory().toString() + WHATSAPP_STATUSES_LOCATION))
        recyclerAdapter = RecyclerAdapter(fileslist!!,null,requireContext());
        imgfragmentbinding.imagesrecycler.adapter = recyclerAdapter





        Handler().postDelayed(Runnable {

            if (fileslist!!.size == recyclerAdapter.itemCount)  imgfragmentbinding.progressBar.visibility = View.GONE

        },500)




        return imgfragmentbinding.root;
    }


}