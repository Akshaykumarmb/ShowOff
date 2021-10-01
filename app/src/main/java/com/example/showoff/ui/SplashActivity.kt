package com.example.showoff.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.showoff.R
import com.example.showoff.ShowOffApplication
import com.example.showoff.Singleton.DataSingleton
import com.example.showoff.ViewModel.APIViewModel
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    private lateinit var apiViewModel: APIViewModel
    private var animationFinished = false
    private var apiCallDone = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        apiViewModel = ViewModelProvider(this).get(APIViewModel::class.java)


        startAnimation()
        calCatAPI()

    }

    fun startAnimation() {

        val rotate = RotateAnimation(0F, 180F, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 1500
        rotate.interpolator = LinearInterpolator()

        logo_view.startAnimation(rotate)


        rotate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                progress.visibility = View.VISIBLE
                if(apiCallDone)
                {
                    GotoNextActicvity()
                } else
                {
                    animationFinished = true
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })

    }

    fun calCatAPI() {
        apiViewModel.getCategories()
        apiViewModel.productCategory.observe(this, Observer {
            Log.d("splashActivty", "onCreate: "+it.get(0))
            ShowOffApplication.applicationContext().dataSingleton.categoryList = it
            if(animationFinished) {
                GotoNextActicvity()
            }else{
                apiCallDone = true
            }
        })
    }


    fun GotoNextActicvity()
    {
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}