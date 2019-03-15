package com.ahmaddudayef.technicaltest.di.component

import com.ahmaddudayef.technicaltest.App
import com.ahmaddudayef.technicaltest.di.module.ActivityBindingModule
import com.ahmaddudayef.technicaltest.di.module.ApplicationModule
import com.ahmaddudayef.technicaltest.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Ahmad Dudayef on 13/03/19.
 */
@Singleton
@Component(
    modules =
    [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: App): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: App)
}