package app.nocamelstyle.cocktailguide.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import app.nocamelstyle.cocktailguide.App
import app.nocamelstyle.cocktailguide.R
import app.nocamelstyle.cocktailguide.utils.startActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch {
            delay(2000)
            if (App.setting.isOnboardingShowed)
                startActivity<HomeActivity>()
            else
                startActivity<OnBoardingActivity>()
            finish()
        }
    }

}