package com.example.statussaver.Fragments

import android.os.Bundle
import android.os.Environment
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.statussaver.Adapters.RecyclerAdapter
import com.example.statussaver.Adapters.videosadapter
import com.example.statussaver.Viewmodels.StatusViewModel
import com.example.statussaver.databinding.FragmentVideosBinding
import java.io.File


class Videos_Fragment : Fragment() {


   lateinit var videosfragmentbinding:FragmentVideosBinding
    lateinit var statusmodel : StatusViewModel
    lateinit var recyclerAdapter:videosadapter
    val WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        videosfragmentbinding = FragmentVideosBinding.inflate(layoutInflater)
        statusmodel = ViewModelProvider(this).get(StatusViewModel::class.java)



        var fileslist = statusmodel.getvideofiles(File(Environment.getExternalStorageDirectory().toString() + WHATSAPP_STATUSES_LOCATION))
        recyclerAdapter = videosadapter(fileslist!!,requireContext());
        videosfragmentbinding.videorecyclerr.adapter = recyclerAdapter


        Handler().postDelayed(Runnable {

            if (fileslist.size == recyclerAdapter.itemCount)  videosfragmentbinding.progressBar.visibility = View.GONE

        },1000)


        return videosfragmentbinding.root;
    }

    override fun onResume() {
        super.onResume()

    }


}