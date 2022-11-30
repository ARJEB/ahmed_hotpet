package com.quintus.labs.datingapp.Main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

import tn.esprit.smartpet.view.Home

/**
 * DatingApp
 * https://github.com/quintuslabs/DatingApp
 * Created on 25-sept-2018.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
class BtnLikeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)}}
     /*   setContentView(R.layout.activity_btn_like)
        setupTopNavigationView()
        like = findViewById(R.id.like)
        val intent = intent
        val profileUrl = intent.getStringExtra("url")
        when (profileUrl) {
            "defaultFemale" -> Glide.with(mContext).load(R.drawable.default_woman).into(like)
            "defaultMale" -> Glide.with(mContext).load(R.drawable.default_man).into(like)
            else -> Glide.with(mContext).load(profileUrl).into(like)
        }
        Thread {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val mainIntent = Intent(this@BtnLikeActivity, Home::class.java)
            startActivity(mainIntent)
        }.start()
    }


    private fun setupTopNavigationView() {
        Log.d(TAG, "setupTopNavigationView: setting up TopNavigationView")
        val tvEx: BottomNavigationViewEx = findViewById(R.id.topNavViewBar)
        TopNavigationViewHelper.setupTopNavigationView(tvEx)
        TopNavigationViewHelper.enableNavigation(mContext, tvEx)
        val menu: Menu = tvEx.getMenu()
        val menuItem = menu.getItem(ACTIVITY_NUM)
        menuItem.isChecked = true
    }

    companion object {
        private const val TAG = "BtnLikeActivity"
        private const val ACTIVITY_NUM = 1
    }
}

      */