package com.nineleaps.smarttv.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.nineleaps.smarttv.R
import com.nineleaps.smarttv.adapters.SlidingImage_Adapter
import com.nineleaps.smarttv.activity.MainActivity
import com.viewpagerindicator.CirclePageIndicator
import java.util.*
import kotlin.collections.ArrayList

class ImageSliderFragment : Fragment() {

    private var mPager: ViewPager? = null
    private var currentPage = 0
    private var NUM_PAGES = 0

    lateinit var urls : ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        urls = (activity!! as MainActivity).getListOfImageUrl()
        if (urls != null){
            initSlider()
        }
    }

    private fun initSlider() {
        mPager = activity!!.findViewById(R.id.image_slider_pager) as ViewPager
        mPager!!.setAdapter(
            SlidingImage_Adapter(
                activity!!,
                urls
            )
        )

        val indicator = activity!!.findViewById(R.id.image_slider_indicator)
                as CirclePageIndicator

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density


        indicator.radius = 5 * density

        NUM_PAGES = urls.size

        // Auto start of viewpager
        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 5000, 5000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })

    }
}

