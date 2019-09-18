package com.nineleaps.smarttv

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.viewpagerindicator.CirclePageIndicator
import java.util.*

class ImageSliderFragment : Fragment() {

    private var mPager: ViewPager? = null
    private var currentPage = 0
    private var NUM_PAGES = 0

    private val urls = arrayOf(
        "https://www.gstatic.com/webp/gallery3/2.png",
        "https://demonuts.com/Demonuts/SampleImages/W-03.JPG",
        "https://demonuts.com/Demonuts/SampleImages/W-08.JPG",
        "https://demonuts.com/Demonuts/SampleImages/W-10.JPG",
        "https://demonuts.com/Demonuts/SampleImages/W-13.JPG",
        "https://demonuts.com/Demonuts/SampleImages/W-17.JPG",
        "https://demonuts.com/Demonuts/SampleImages/W-21.JPG"
    )


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initSlider()
    }

    private fun initSlider() {
        mPager = activity!!.findViewById(R.id.image_slider_pager) as ViewPager
        mPager!!.setAdapter(SlidingImage_Adapter(activity!!, urls))

        val indicator = activity!!.findViewById(R.id.image_slider_indicator) as CirclePageIndicator

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

