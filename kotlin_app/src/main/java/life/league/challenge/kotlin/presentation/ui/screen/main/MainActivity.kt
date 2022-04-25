package life.league.challenge.kotlin.presentation.ui.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import life.league.challenge.kotlin.R
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}
