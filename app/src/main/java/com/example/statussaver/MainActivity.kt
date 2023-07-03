package com.example.statussaver

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.statussaver.Adapters.tabAdapter
import com.example.statussaver.Viewmodels.StatusViewModel
import com.example.statussaver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var tabAdapter: tabAdapter
    lateinit var mainBinding: ActivityMainBinding
    lateinit var statusViewModel: StatusViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        statusViewModel = ViewModelProvider(this).get(StatusViewModel::class.java)

        var fm = supportFragmentManager
        tabAdapter = tabAdapter(fm);

        mainBinding.tab.setupWithViewPager(mainBinding.pager)
        mainBinding.pager.adapter = tabAdapter

        askpermissions()

        statusViewModel.createFolder()




    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100 && grantResults[0] == RESULT_OK){

            recreate()
        }
    }

    fun askpermissions(){

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.MANAGE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_DENIED &&
            ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_DENIED &&
            ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_DENIED

                ){

            var permissions = arrayOf(Manifest.permission.MANAGE_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, permissions,100)
        }


    }

    override fun onBackPressed() {

        finishAffinity()
    }
}