package org.shibajs.shiba

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class ShibaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.hasExtra("targetComponent")) {

        } else {
            if (Shiba.appComponent == null) {
                // TODO: Init
            }
            setContentView(ShibaHost(this).apply {
                viewComponent = Shiba.appComponent
            })
        }
    }
}