package com.example.shifttest.di

import com.example.shifttest.presentation.user_info.UserInfoFragment
import com.example.shifttest.presentation.user_list.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun userListFragment(): UserListFragment

    @ContributesAndroidInjector
    abstract fun userInfoFragment(): UserInfoFragment

}