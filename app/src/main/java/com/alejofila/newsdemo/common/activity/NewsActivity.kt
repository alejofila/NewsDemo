package com.alejofila.newsdemo.common.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.alejofila.newsdemo.R
import com.alejofila.newsdemo.browseNews.fragment.NewsListFragment

class NewsActivity : AppCompatActivity(), FragmentNavigationCallback {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        if(supportFragmentManager.findFragmentById(R.id.content) == null){
            navigateTo(NewsListFragment.newInstance(),false)
        }


    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {
        if(addToBackStack) {
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left , android.R.anim.slide_out_right)
                    .replace(R.id.content, fragment)
                    .addToBackStack(null)
                    .commit()
        }
        else{
            supportFragmentManager.beginTransaction()
                    .add(R.id.content, fragment)
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .commit()
        }
    }


}

interface FragmentNavigationCallback {
    fun navigateTo(fragment: Fragment, addToBackStack: Boolean)
}
