package com.nineleaps.smarttv.fragment


import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.nineleaps.smarttv.R
import com.nineleaps.smarttv.activity.MainActivity


class VideoViewFragment : Fragment() {
    private lateinit var videoView: VideoView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_video_view, container, false)
        videoView = view.findViewById(R.id.video_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playVideo()
    }


    /**
     * load a web view with a URL
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun playVideo() {
        val videoUrl = (activity as MainActivity).webUrl
        val uri = Uri.parse(videoUrl)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

    }
}
