package com.example.hands_market

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import java.net.URISyntaxException

class BWebviewClient : WebViewClient(){
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        val intent = parse(url)
        return if (isIntent(url)) {
            if (isExistInfo(intent,view.context) or isExistPackage(intent,view.context))
                start(intent,view.context)
            else
                gotoMarket(intent,view.context)
        } else if (isMarket(url))
            start(intent,view.context)
        else
            url.contains("https://bootpaymark")
    }
    private fun isIntent(url: String?) = url?.matches(Regex("^intent:?\\w*://\\S+$")) ?: false

    private fun isMarket(url: String?) = url?.matches(Regex("^market://\\S+$")) ?: false

    private fun isExistInfo(intent: Intent?, context :Context): Boolean {
        return try {
            intent != null && context.packageManager.getPackageInfo(intent.`package`, PackageManager.GET_ACTIVITIES) != null
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

    }

    private fun isExistPackage(intent: Intent?,context: Context): Boolean =
        intent != null && intent.`package`?.let {
            context.packageManager.getLaunchIntentForPackage(
                it
            )
        } != null

    private fun parse(url: String): Intent? {
        return try {
            Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
        } catch (e: URISyntaxException) {
            null
        }

    }

    private fun start(intent: Intent?,context: Context): Boolean {
        intent?.let { context.startActivity(it) }
        return true
    }
    private fun gotoMarket(intent: Intent?,context: Context): Boolean {
        return true
    }
}