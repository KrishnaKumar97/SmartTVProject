package com.nineleaps.smarttv.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.nineleaps.smarttv.R
import com.nineleaps.smarttv.activity.MainActivity


class WebViewFragment : Fragment() {
    lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)
        webView = view.findViewById(R.id.web_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLinkToWebView()
    }


    /**
     * load a web view with a URL
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun setLinkToWebView() {
        val webUrl = (activity as MainActivity).getWebUrlForFragment()

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.overScrollMode = WebView.OVER_SCROLL_NEVER
        webView.loadUrl(webUrl)

    }
}
