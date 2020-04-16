package com.pacific.app.adapter.base

import android.app.ActivityManager
import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.pacific.app.adapter.BuildConfig
import java.io.InputStream

@GlideModule
@Excludes(OkHttpLibraryGlideModule::class)
class MyOkHttpLibraryGlideModule : AppGlideModule() {

    override fun isManifestParsingEnabled() = false

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        // Prefer higher quality images unless we're on a low RAM device
        val defaultOptions = RequestOptions().format(
            if (am.isLowRamDevice) {
                DecodeFormat.PREFER_RGB_565
            } else {
                DecodeFormat.PREFER_ARGB_8888
            }
        )
        // Disable hardware bitmaps as they don't play nicely with Palette
        defaultOptions.disallowHardwareConfig()
        builder.setDefaultRequestOptions(defaultOptions)
        if (BuildConfig.DEBUG) {
            builder.setLogLevel(Log.VERBOSE)
        } else {
            builder.setLogLevel(Log.ERROR)
        }
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(createPoorSSLOkHttpClient())
        )
    }
}