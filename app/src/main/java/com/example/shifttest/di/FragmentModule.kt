package com.example.shifttest.di

import com.example.shifttest.presentation.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun userListFragment(): UserListFragment

}