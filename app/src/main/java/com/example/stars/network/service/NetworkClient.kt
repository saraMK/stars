package com.example.stars.network.service

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


const val BASE_URL = "https://api.themoviedb.org/3/"
const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"
const val ORIGIN_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
private const val TIMEOUT_MIN = 1
lateinit var retrofit: Retrofit
val API_KEY = "6657d7af64bf41b3f94254646a08a504"


val networkModule_ = module {
    single { getUnsafeOkHttpClientAuth() }
    single { getRetrofit(get()) }
    single { getNetworkService(get()) }
}


fun getRetrofit(claint: OkHttpClient): Retrofit? {

    val gson = GsonBuilder()
        .setLenient()
        .create()

    retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(claint)
        .build();
    return retrofit
}


fun getNetworkService(retrofit: Retrofit): NetworkApis {
    // networkInterfaceApis = retrofit.create(NetworkApis::class.java)
    return retrofit.create(NetworkApis::class.java)
}

fun getUnsafeOkHttpClientAuth(): OkHttpClient {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts =
            arrayOf<TrustManager>(
                object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )

        // Install the all_en-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all_en-trusting manager
        val sslSocketFactory = sslContext.socketFactory
        val interceptor1 = HttpLoggingInterceptor()
        interceptor1.setLevel(HttpLoggingInterceptor.Level.BODY)
        val interceptor =
            Interceptor { chain: Interceptor.Chain ->
                var request: Request = chain.request()
                val httpUrl: HttpUrl = request.url()
                val url = httpUrl.newBuilder()
                    .addQueryParameter("lang", "en").addQueryParameter("api_key", API_KEY).build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            }
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(
            TIMEOUT_MIN.toLong(),
            TimeUnit.MINUTES
        )
        builder.readTimeout(
            TIMEOUT_MIN.toLong(),
            TimeUnit.MINUTES
        )
        builder.addInterceptor(interceptor).build()
        // for logged msgs
        builder.addInterceptor(interceptor1).build()
        builder.sslSocketFactory(
            sslSocketFactory,
            trustAllCerts[0] as X509TrustManager
        )
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}




