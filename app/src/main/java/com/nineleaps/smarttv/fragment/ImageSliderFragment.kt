package com.nineleaps.smarttv.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.nineleaps.smarttv.R
import com.nineleaps.smarttv.activity.MainActivity
import com.nineleaps.smarttv.adapters.SlidingImageAdapter
import com.viewpagerindicator.CirclePageIndicator
import java.util.*

class ImageSliderFragment : Fragment() {

    private var mPager: ViewPager? = null
    private var swipeTimer: Timer? = null
    private var currentPage = 0
    private var pageCount = 0
    private val periodTime: Long = 3000
    private var handler: Handler? = null

    lateinit var urls: ArrayList<String>

    /**
     * This is an override function for onCreate View
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_slider, container, false)
    }

    /**
     * This is an override function for onView Created
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        urls = arrayListOf()
        initSlider()
    }

    /**
     * This function initializes the slider
     */
    private fun initSlider() {
        urls = (activity!! as MainActivity).getListOfImageUrl()
        mPager = activity!!.findViewById(R.id.image_slider_pager) as ViewPager
        mPager!!.adapter = SlidingImageAdapter(
            activity!!,
            urls
        )
        setHandler()
        setListenerForIndicators()
    }

    /**
     * This function sets the listeners for the indicators
     */
    private fun setListenerForIndicators() {
        val indicator = activity!!.findViewById(R.id.image_slider_indicator)
                as CirclePageIndicator
        val density = resources.displayMetrics.density

        indicator.setViewPager(mPager)
        indicator.radius = 5 * density
        pageCount = urls.size

        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position
            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {
                //No-op
            }

            override fun onPageScrollStateChanged(pos: Int) {
                //No-op
            }
        })

    }

    /**
     * This function sets the handler
     */
    private fun setHandler() {
        handler = Handler()
        swipeTimer = Timer()
        val update = Runnable {
            if (currentPage == pageCount) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }

        swipeTimer?.schedule(object : TimerTask() {
            override fun run() {
                handler?.post(update)
            }
        }, 0, periodTime)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacksAndMessages(null)
        swipeTimer?.cancel()
    }
}

