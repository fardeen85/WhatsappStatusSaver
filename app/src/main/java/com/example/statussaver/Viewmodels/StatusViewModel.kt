package com.example.statussaver.Viewmodels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.speech.tts.TextToSpeech.EngineInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


class StatusViewModel : ViewModel() {



    val WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses"

    init {


    }


     fun getListFiles(parentDir: File): ArrayList<File>? {

         Log.d("Tag","called")

        val inFiles = ArrayList<File>()
         var bitmaps = ArrayList<Bitmap>();

         viewModelScope.launch(Dispatchers.IO){

            val files: Array<File>?
            files = parentDir.listFiles()
            if (files != null) {
                for (file in files) {
                    Log.e("check", file.name)
                    if (file.name.endsWith(".jpg") ||
                        file.name.endsWith(".gif")
                       // file.name.endsWith(".mp4")
                    ) {
                        if (!inFiles.contains(file)) inFiles.add(file)
                        Log.d("Tag",file.toString())
                        var bm = BitmapFactory.decodeFile(file.absolutePath)

                        //bitmapslive.value = bitmaps



                    }

            }

        }

         }
        return inFiles
    }





    fun getListSavedFiles(parentDir: File): ArrayList<File>? {


        val inFiles = ArrayList<File>()
        viewModelScope.launch(Dispatchers.IO) {


            Log.d("Tag", "called")



            val files: Array<File>?
            files = parentDir.listFiles()
            if (files != null) {
                for (file in files) {
                    Log.e("check", file.name)
                    if (file.name.endsWith(".jpg") ||
                        file.name.endsWith(".gif") ||
                        file.name.endsWith(".mp4")
                    // file.name.endsWith(".mp4")
                    ) {
                        if (!inFiles.contains(file)) inFiles.add(file)
                        Log.d("Tag", file.toString())

                    }

                }

            }
        }

        return inFiles
    }




    fun getvideofiles(parentDir: File): ArrayList<File>?{

        val inFiles = ArrayList<File>()
        viewModelScope.launch(Dispatchers.Default) {


            val files: Array<File>?
            files = parentDir.listFiles()
            if (files != null) {
                for (file in files) {
                    Log.e("check", file.name)
                    if (file.name.endsWith(".mp4")
                    ) {
                        if (!inFiles.contains(file)) inFiles.add(file)
                        Log.d("Tag",file.toString())

                    }

                }

            }



        }

        return inFiles

    }

    fun createFolder(){


        viewModelScope.launch(Dispatchers.IO) {


            var file = File(Environment.getExternalStorageDirectory(),"SavedStatus")
            if(file.exists()){

                Log.d("Tag","Directory already exists")
            }
            else{

                file.mkdir()

                if (file.isDirectory){

                    Log.d("Tag","Directory created")
                }
                else{

                    Log.d("Tag","failed to create directroy at"+"\npath :"+Environment.getExternalStorageDirectory()+
                    "\nmkdirs :"+file.mkdir())
                }
            }

        }

    }


}