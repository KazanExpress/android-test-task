package app.nocamelstyle.cocktailguide.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class OnBoardingPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager) {

    private val fragments = ArrayList<Fragment>()

    fun pushFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    override fun getCount() = fragments.size

    override fun getItem(position: Int) = fragments[position]

}