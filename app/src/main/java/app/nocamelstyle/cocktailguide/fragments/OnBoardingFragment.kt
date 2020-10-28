package app.nocamelstyle.cocktailguide.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.nocamelstyle.cocktailguide.R
import kotlinx.android.synthetic.main.fragment_on_boarding.*

class OnBoardingFragment(val data: String) : Fragment(R.layout.fragment_on_boarding) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        info.text = data
    }

}