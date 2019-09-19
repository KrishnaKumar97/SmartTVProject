package com.nineleaps.smarttv.adapters

import android.content.Context
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.nineleaps.smarttv.R

class SlidingImageAdapter(private val context: Context, private val urls: ArrayList<String>) :
    PagerAdapter() {

    // Variables
    private val inflater: LayoutInflater = LayoutInflater.from(context)


    /**
     * This function is called for destroy item
     */
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    /**
     * This is an override function for getting the count of url list
     */
    override fun getCount(): Int {
        return urls.size
    }

    /**
     * This function instate Item
     */
    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(
            R.layout.slidingimages_layout, view,
            false
        )
        val imageView = imageLayout.findViewById<View>(R.id.image) as ImageView
        Glide.with(context)
            .asBitmap()
            .load(urls[position])
            .override(1600, 1600)
            .thumbnail(.1f)
            .fitCenter()
            .into(object : BitmapImageViewTarget(imageView) {

            })
        view.addView(imageLayout, 0)
        return imageLayout
    }

    /**
     * This function is to get View from object
     */
    override fun isViewFromObject(view: View, value: Any): Boolean {
        return view == value
    }

    /**
     * This function is an override function for restoring state
     */
    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    /**
     * This is an override function for save state
     */
    override fun saveState(): Parcelable? {
        return null
    }


}