package com.nineleaps.smarttv.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.nineleaps.smarttv.R
import com.nineleaps.smarttv.activity.MainActivity


class DefaultImageFragment : Fragment() {
    private lateinit var imageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_default_image, container, false)
        imageView = view.findViewById(R.id.image_view)
                return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImageFromUrl()
    }


    /**
     * load a image from the image Url
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun loadImageFromUrl() {
        val imageUrl = (activity as MainActivity).imageUrl
        Glide.with(this)
            .load(imageUrl)
            .fitCenter()
            .into(imageView)
    }
}
