package com.example.statussaver.Adapters

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.statussaver.ActivityPlayVideo
import com.example.statussaver.R
import com.example.statussaver.databinding.Row3Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.util.ArrayList

class savedAdapter(var datalist : ArrayList<File>,var c: Context) : RecyclerView.Adapter<savedAdapter.myviewHolder>() {

    class myviewHolder(var row : Row3Binding) : RecyclerView.ViewHolder(row.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewHolder {
        var row = Row3Binding.inflate(LayoutInflater.from(parent.context))
        return  myviewHolder(row)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    override fun onBindViewHolder(holder: myviewHolder, position: Int) {

        var data = datalist.get(position)
        holder.row.btnplay.visibility = View.GONE

        GlobalScope.launch {

            withContext(Dispatchers.Main) {

                if (data.absolutePath.contains("jpg") || data.absolutePath.contains("JPG")
                    || data.absolutePath.contains("png") || data.absolutePath.contains("PNG")
                    || data.absolutePath.contains("GIF") || data.absolutePath.contains("gif")
                ) {

                    Glide.with(holder.row.image).load(data.absolutePath).placeholder(R.drawable.placeholder).into(holder.row.image)
                    holder.row.btnplay.visibility = View.GONE

                }


               else if (data.absolutePath.contains("MP4") || data.absolutePath.contains("mp4")){


                    Glide.with(holder.row.image).load(data.absolutePath).into(holder.row.image)
                    holder.row.btnplay.visibility = View.VISIBLE

                }
                else{

                    Log.d("Tag","there is some errorr")
                }

            }
        }

        holder.row.btnplay.setOnClickListener {

            var i = Intent(c, ActivityPlayVideo::class.java)
            i.putExtra("path",data.absolutePath)
            c.startActivity(i)
        }

    }
}