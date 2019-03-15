package com.ahmaddudayef.technicaltest.di.module

import android.app.Application
import android.content.Context
import com.ahmaddudayef.technicaltest.App
import com.ahmaddudayef.technicaltest.BuildConfig
import com.ahmaddudayef.technicaltest.data.AppDataManager
import com.ahmaddudayef.technicaltest.data.DataManager
import com.ahmaddudayef.technicaltest.data.network.ApiHelper
import com.ahmaddudayef.technicaltest.data.network.AppApiHelper
import com.ahmaddudayef.technicaltest.di.ApplicationContext
import com.ahmaddudayef.technicaltest.di.BaseUrl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
@Module
class ApplicationModule {

    @Provides
    @ApplicationContext
    internal fun provideContext(app: App): Context {
        return app.applicationContext
    }

    @Provides
    internal fun provideApplication(app: App): Application {
        return app
    }

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @BaseUrl
    internal fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Singleton
    internal fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }
}