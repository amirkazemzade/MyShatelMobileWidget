package me.amirkazemzade.netwidget

import android.app.Application
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import me.amirkazemzade.netwidget.presentation.RemainedWorkerFactory
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: RemainedWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()
}