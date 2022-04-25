package life.league.challenge.kotlin

import android.app.Application
import life.league.challenge.kotlin.data.dataModule
import life.league.challenge.kotlin.presentation.ui.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Setup Koin
        startKoin {
            androidLogger(Level.ERROR) // Koin Android Logger
            androidContext(this@App) // Inject Android context
            modules(dataModule, presentationModule) // Modules
        }
    }

}