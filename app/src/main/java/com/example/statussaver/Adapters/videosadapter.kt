package com.example.statussaver.Adapters

import android.R.attr.thumb
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.statussaver.ActivityPlayVideo
import com.example.statussaver.R
import com.example.statussaver.databinding.Row2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class videosadapter(var datalist : ArrayList<File>, var c: Context) : RecyclerView.Adapter<videosadapter.myViewHolder>() {



    class  myViewHolder(var row : Row2Binding) : RecyclerView.ViewHolder(row.root){



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        var row = Row2Binding.inflate(LayoutInflater.from(parent.context))
//        val height = parent.measuredHeight
//        val width = parent.measuredWidth/4
//
//        row.root.setLayoutParams(RecyclerView.LayoutParams(width, height))
        return  myViewHolder(row)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        var data = datalist.get(position)

        GlobalScope.launch(Dispatchers.IO) {

            withContext(Dispatchers.Main) {


                Glide.with(holder.row.image).load(data.absolutePath).into(holder.row.image)


            }
        }

        holder.row.btnshare.setOnClickListener {

            val sharingIntent = Intent(Intent.ACTION_SEND)
            val screenshotUri =
                Uri.parse(data.absolutePath.toString())

            sharingIntent.type = "image/*"
            sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
            Log.d("TAG","get absolute path"+data.absolutePath)
            c.startActivity(Intent.createChooser(sharingIntent,"choose an app to share"))
        }

        holder.row.btnplay.setOnClickListener {

            var i = Intent(c,ActivityPlayVideo::class.java)
            i.putExtra("path",data.absolutePath)
            c.startActivity(i)
        }


        holder.row.btndownload.setOnClickListener {

            CoroutineScope(Dispatchers.Default).launch {

                withContext(Dispatchers.Main) {



                    val sourcePath =
                        data.absolutePath
                    val source = File(sourcePath)

                    val destinationPath =
                        Environment.getExternalStorageDirectory().toString() + "/SavedStatus/"

                    var newfile = File(destinationPath+data.name)

                    if (newfile.exists()){

                        Toast.makeText(c,"File already downloaded", Toast.LENGTH_SHORT).show()
                    }
                    else {

                        try {
                            copyFile(source, destinationPath + data.name)

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }





    }

    fun copyFile(source:File, destination:String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            FileUtils.copy(FileInputStream(source), FileOutputStream(destination))
            Toast.makeText(c,"Downloaded succesfully",Toast.LENGTH_SHORT).show()


        }

        else{

            copy(source,destination)
            Toast.makeText(c,"Downloaded succesfully",Toast.LENGTH_SHORT).show()
            Log.d("Tag","not android 10")
        }



    }


    @Throws(IOException::class)
    fun copy(src: File?, dst: String?) {
        val `in`: InputStream = FileInputStream(src)
        try {
            val out: OutputStream = FileOutputStream(dst)
            try {
                // Transfer bytes from in to out
                val buf = ByteArray(1024)
                var len: Int
                while (`in`.read(buf).also { len = it } > 0) {
                    out.write(buf, 0, len)
                }
            } finally {
                out.close()
            }
        } finally {
            `in`.close()
        }
    }
}
