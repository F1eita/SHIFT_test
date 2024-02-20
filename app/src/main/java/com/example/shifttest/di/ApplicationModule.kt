package com.example.shifttest.di

import android.content.Context
import com.example.shifttest.ShiftApplication
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule {

    @Provides
    fun provideApplicationContext(app: ShiftApplication): Context {
        return app.applicationContext
    }
}