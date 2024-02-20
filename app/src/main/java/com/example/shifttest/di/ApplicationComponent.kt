package com.example.shifttest.di

import com.example.shifttest.ShiftApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<ShiftApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: ShiftApplication): ApplicationComponent
    }
}