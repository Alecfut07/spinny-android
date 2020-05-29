package com.alexiscrack3.spinny

import com.google.gson.Gson
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object NetworkModule : KoinComponent {
    private lateinit var retrofit: Retrofit

    fun init() {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        }
        val httpLoggingInterceptor = HttpLoggingInterceptor(logger)
        val okHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        retrofit = buildRetrofit(BuildConfig.BASE_URL, okHttpClient, Gson())
    }

    fun <S> createService(service: Class<S>): S = retrofit.create(service)
}

private fun buildRetrofit(url: String, okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
        )
        .build()
}
