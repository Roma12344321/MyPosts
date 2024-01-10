package com.dev.myposts.presentation.activity

import android.app.Application
import com.dev.myposts.di.DaggerApplicationComponent

class PostApp : Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}