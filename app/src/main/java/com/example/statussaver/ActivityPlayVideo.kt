package com.example.statussaver

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.statussaver.databinding.ActivityPlayVideoBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import java.io.File

class ActivityPlayVideo : AppCompatActivity() {

    lateinit var activityPlayVideoBinding: ActivityPlayVideoBinding
    lateinit var simpleExoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPlayVideoBinding = ActivityPlayVideoBinding.inflate(layoutInflater)
        setContentView(activityPlayVideoBinding.root)


        var ex = intent.extras

        Log.d("Tag",ex!!.getString("path")!!)

        var fileuri = Uri.fromFile(File(ex!!.getString("path")!!))

        var videouri = Uri.parse(ex!!.getString("path"))

        Log.d("Tag",fileuri.toString())

        //Exoplayer
        initializeExoPlayer(videouri)


        activityPlayVideoBinding.idExoPlayerVIew

    }

    fun initializeExoPlayer(videorui:Uri){
        simpleExoPlayer = ExoPlayer.Builder(this).build()
        activityPlayVideoBinding.idExoPlayerVIew.player = simpleExoPlayer
        val mediaItem: MediaItem = MediaItem.fromUri(videorui)
        simpleExoPlayer.setMediaItem(mediaItem)
        simpleExoPlayer.prepare()
        simpleExoPlayer.play()

    }
}