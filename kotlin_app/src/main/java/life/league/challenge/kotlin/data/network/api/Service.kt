package life.league.challenge.kotlin.data.network.api

import life.league.challenge.kotlin.data.network.generator.NetworkRequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Service(private val networkRequestInterceptor: NetworkRequestInterceptor) {

    companion object {
        private const val HOST = "https://engineering.league.dev/challenge/api/"
    }

    val api: Api by lazy {
        val retrofit = Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(Api::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            // Build OkHttpClient
            .followRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(networkRequestInterceptor)
        return builder.build()
    }
}
