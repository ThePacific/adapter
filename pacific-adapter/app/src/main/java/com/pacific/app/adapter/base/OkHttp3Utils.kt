package com.pacific.app.adapter.base

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier

fun createPoorSSLOkHttpClient(): OkHttpClient {
    val dataModule = DataHttpModule()
    val poorX509TrustManager = dataModule.providePoorX509TrustManager()
    val poorSSLContext = dataModule.providePoorSSLContext(poorX509TrustManager)
    return OkHttpClient.Builder()
        .sslSocketFactory(poorSSLContext.socketFactory, poorX509TrustManager)
        .hostnameVerifier(HostnameVerifier { _, _ -> true })
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}