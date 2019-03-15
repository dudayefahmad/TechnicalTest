package com.ahmaddudayef.technicaltest.di.module

import com.ahmaddudayef.technicaltest.ui.main.MainActivity
import com.ahmaddudayef.technicaltest.ui.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [(MainModule::class)])
    abstract fun bindMainActivity(): MainActivity
}