package com.pacific.app.adapter.base

import android.util.Log
import com.pacific.app.adapter.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class DataHttpModule {

    @Provides
    @Singleton
    fun providePoorX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()

            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }
        }
    }

    @Provides
    @Singleton
    fun providePoorSSLContext(x509TrustManager: X509TrustManager): SSLContext {
        try {
            return Platform.get().newSSLContext().apply {
                init(null, arrayOf<TrustManager>(x509TrustManager), SecureRandom())
            }
        } catch (e: NoSuchAlgorithmException) {
            throw e
        } catch (e: KeyManagementException) {
            throw e
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptorLogger(): HttpLoggingInterceptor.Logger {
        return object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.e("OkHttp->", message)
            }
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(
        httpLoggingInterceptorLogger: HttpLoggingInterceptor.Logger
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(httpLoggingInterceptorLogger).apply {
            this.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}