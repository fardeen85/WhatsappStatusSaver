package com.example.statussaver.Adapters

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.FileUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.statussaver.R
import com.example.statussaver.databinding.RowBinding
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


class RecyclerAdapter(var datalist : ArrayList<File>,var bitmapslive : ArrayList<Bitmap>?,var c:Context) : RecyclerView.Adapter<RecyclerAdapter.myViewHolder>() {



    class  myViewHolder(var row : RowBinding) : RecyclerView.ViewHolder(row.root){



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        var row = RowBinding.inflate(LayoutInflater.from(parent.context))
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
      //  var bitmapatpos = bitmapslive.get(position)



      //  val myBitmap = BitmapFactory.decodeFile(data.getAbsolutePath())

        Log.d("Tag",data.absolutePath.toString())

        if (data.absolutePath != null) {
          //  holder.row.image.setImageBitmap(bitmapatpos)
            Glide.with(holder.row.image).load(data.absoluteFile).placeholder(R.drawable.placeholder).into(holder.row.image)
        }


        holder.row.btndownload.setOnClickListener {

            GlobalScope.launch {

                withContext(Dispatchers.Main) {

                    holder.row.progress.visibility = View.VISIBLE
                    holder.row.btndownload.visibility = View.GONE

                    val sourcePath =
                        data.absolutePath
                    val source = File(sourcePath)

                    val destinationPath =
                        Environment.getExternalStorageDirectory().toString() + "/SavedStatus/"

                    var newfile = File(destinationPath+data.name)

                    if (newfile.exists()){

                        Toast.makeText(c,"File already downloaded",Toast.LENGTH_SHORT).show()
                        holder.row.progress.visibility = View.GONE
                        holder.row.btndownload.visibility = View.VISIBLE
                    }
                    else {

                        try {
                            copyFile(source, destinationPath + data.name,holder)

                        } catch (e: IOException) {
                            e.printStackTrace()
                            holder.row.progress.visibility = View.GONE
                            holder.row.btndownload.visibility = View.VISIBLE
                        }
                    }
                }
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






    }

    fun copyFile(source:File, destination:String,holder: myViewHolder){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            FileUtils.copy(FileInputStream(source), FileOutputStream(destination))
            Toast.makeText(c,"Downloaded succesfully",Toast.LENGTH_SHORT).show()
            holder.row.progress.visibility = View.GONE
            holder.row.btndownload.visibility = View.VISIBLE


        }

        else{

            copy(source,destination)
            Toast.makeText(c,"Downloaded succesfully",Toast.LENGTH_SHORT).show()
            Log.d("Tag","not android 10")
            holder.row.progress.visibility = View.GONE
            holder.row.btndownload.visibility = View.VISIBLE
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