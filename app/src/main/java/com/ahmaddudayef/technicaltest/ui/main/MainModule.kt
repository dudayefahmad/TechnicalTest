package com.ahmaddudayef.technicaltest.ui.main

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.ahmaddudayef.technicaltest.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
@Module
class MainModule {

    @Provides
    fun provideMainMvpPresenter(presenter: MainPresenter<MainMvpView>): MainMvpPresenter<MainMvpView>{
        return presenter
    }

    @Provides
    fun provideLinearLayoutManager(@ApplicationContext context: Context): LinearLayoutManager {
        return LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    @Provides
    fun provideMainAdapter(): MainAdapter {
        return MainAdapter(ArrayList())
    }
}