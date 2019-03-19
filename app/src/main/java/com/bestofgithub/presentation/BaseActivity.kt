package com.bestofgithub.presentation

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.bestofgithub.dagger.components.AppComponent
import com.bestofgithub.dagger.components.DaggerAppComponent
import com.bestofgithub.dagger.modules.RetrofitModule

abstract class BaseActivity : AppCompatActivity() {

    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setUpDaggerGraph()
    }

    fun setUpDaggerGraph() {
        appComponent = DaggerAppComponent
            .builder()
            .retrofitModule(RetrofitModule())
            .build()
    }

    @LayoutRes
    protected abstract fun getLayout(): Int
}