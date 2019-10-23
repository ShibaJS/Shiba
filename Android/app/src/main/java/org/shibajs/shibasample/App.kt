package org.shibajs.shibasample

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import org.shibajs.shiba.Shiba

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

        Shiba.init(this)
    }
}