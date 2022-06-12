package `in`.pawanjeswani.app10xweather.network

import `in`.pawanjeswani.app10xweather.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private var retrofit: Retrofit? = null

    val retroFitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl("BuildConfig.BASE_URL")
            .addConverterFactory(GsonConverterFactory.create())

    }

    val apiService: APIService by lazy {
        retroFitBuilder.apply {
            if (BuildConfig.DEBUG) {
                this.client(getClient())
            }
        }
        return@lazy retroFitBuilder.build().create(APIService::class.java)

    }

    fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
}
