package dev.dbikic.oktaloginexample.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {

    lateinit var retrofit: Retrofit

    private const val BASE_URL = "http://10.0.2.2:8080/"

    private var token = ""

    val retrofitInstance: Retrofit
        get() {
            if (!this::retrofit.isInitialized) { // <1>
                val headersInterceptor = Interceptor { chain ->  // <2>
                    val requestBuilder = chain.request().newBuilder()
                    requestBuilder.header("Authorization", "Bearer $token")  // <3>
                    chain.proceed(requestBuilder.build())
                }
                val okHttpClient = OkHttpClient()  // <4>
                    .newBuilder()
                    .followRedirects(true)
                    .addInterceptor(headersInterceptor)  // <5>
                    .build()
                retrofit = Retrofit.Builder() // <6>
                    .baseUrl(BASE_URL) // <7>
                    .addConverterFactory(GsonConverterFactory.create()) // <8>
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // <9>
                    .client(okHttpClient) // <10>
                    .build()
            }
            return retrofit
        }


    fun setToken(token: String) { // <11>
        RetrofitClientInstance.token = token
    }
}