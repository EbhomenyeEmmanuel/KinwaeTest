package com.example.kinwaetest.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.kinwaetest.R
import com.example.kinwaetest.databinding.FragmentIntroBinding
import com.example.kinwaetest.domain.model.onboarding.ScreenItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IntroFragment : Fragment(R.layout.fragment_intro) {
    lateinit var bind: FragmentIntroBinding
    lateinit var viewPagerAdapter: IntroViewPagerAdapter
    lateinit var screenPager: ViewPager2
    lateinit var tabLayout: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list = listOf<ScreenItem>( ScreenItem(
                getString(R.string.onboarding_title_intro2),
                getString(R.string.onboarding_intro_description),
                R.drawable.image_girl
            ),ScreenItem(
                getString(R.string.onboarding_title_intro1),
                getString(R.string.onboarding_intro_description),
                R.drawable.image_market
            ),
            ScreenItem(
                getString(R.string.onboarding_title_intro3),
                getString(R.string.onboarding_intro_description),
                R.drawable.image_man
            ),
        )

        screenPager = bind.screenViewPager
        viewPagerAdapter = IntroViewPagerAdapter(list)
        screenPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        screenPager.adapter = viewPagerAdapter

        tabLayout = bind.tabLayout

        bind.loginButton.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.introFragment)
                findNavController().navigate(R.id.action_introFragment_to_loginFragment)
        }

        bind.createAccountButton.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.introFragment)
                findNavController().navigate(R.id.action_introFragment_to_signUpFragment)
        }

        TabLayoutMediator(tabLayout, screenPager) { tab, position ->
            screenPager.currentItem = tab.position
        }.attach()
        setUpTabMargin()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentIntroBinding.inflate(layoutInflater)
        return bind.root
    }

//    screenPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            super.onPageSelected(position);
//
//            Log.e("Selected_Page", String.valueOf(position));
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//            super.onPageScrollStateChanged(state);
//        }
//    });


    private fun setUpTabMargin() {
        for (i in 0 until tabLayout.tabCount) {
            val tab: View = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as MarginLayoutParams
            p.setMargins(0, 0, 20, 0)
            tab.requestLayout()
        }
    }
}