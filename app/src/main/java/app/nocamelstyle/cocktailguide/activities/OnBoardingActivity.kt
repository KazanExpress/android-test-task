package app.nocamelstyle.cocktailguide.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import app.nocamelstyle.cocktailguide.App
import app.nocamelstyle.cocktailguide.R
import app.nocamelstyle.cocktailguide.adapters.OnBoardingPagerAdapter
import app.nocamelstyle.cocktailguide.fragments.OnBoardingFragment
import app.nocamelstyle.cocktailguide.utils.startActivity
import kotlinx.android.synthetic.main.activity_on_boarding.*

class OnBoardingActivity : AppCompatActivity(R.layout.activity_on_boarding) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.setting.isOnboardingShowed = true

        val adapter = OnBoardingPagerAdapter(supportFragmentManager).apply {
            pushFragment(OnBoardingFragment(getString(R.string.hello_blank_fragment)))
            pushFragment(OnBoardingFragment(getString(R.string.hello_blank_fragment)))
            pushFragment(OnBoardingFragment(getString(R.string.hello_blank_fragment)))
        }
        container.adapter = adapter
        dots_indicator.setViewPager(container)
    }

    fun start(v: View) {
        startActivity<HomeActivity>()
        finish()
    }
}