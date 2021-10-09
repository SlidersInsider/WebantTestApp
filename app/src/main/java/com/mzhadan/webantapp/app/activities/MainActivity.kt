package com.mzhadan.webantapp.app.activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mzhadan.webantapp.R
import com.mzhadan.webantapp.app.fragments.NewPageFragment
import com.mzhadan.webantapp.app.fragments.NoInternetFragment
import com.mzhadan.webantapp.app.fragments.PopularPageFragment

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var fragmentManager: FragmentManager
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavViewFun()
        checkNetworkConnection(NewPageFragment(supportFragmentManager),"New")

    }

    fun bottomNavViewFun(){
        bottomNavigationView = findViewById(R.id.bottomNavView)

        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.newMenuItem -> {
                    checkNetworkConnection(NewPageFragment(supportFragmentManager),"New")

                    true
                }
                R.id.popularMenuItem -> {
                    checkNetworkConnection(PopularPageFragment(supportFragmentManager),"Popular")

                    true
                }
                else -> true
            }
        }
    }

    fun replaceFragment(fragmentName: Fragment){
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainFragmentContainer, fragmentName)
        fragmentTransaction.commit()
    }

    fun isNetworkAvailable(context: Context): Boolean {
        var connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }

    fun checkNetworkConnection(fragmentName: Fragment, label: String){
        if(isNetworkAvailable(this)){
            replaceFragment(fragmentName)
            title = label
        }
        else{
            replaceFragment(NoInternetFragment())
            title = label
        }
    }


}