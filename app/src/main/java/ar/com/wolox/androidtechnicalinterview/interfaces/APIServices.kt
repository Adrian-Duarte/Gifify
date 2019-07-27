package ar.com.wolox.androidtechnicalinterview.interfaces

import ar.com.wolox.androidtechnicalinterview.models.GiphyResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import okhttp3.HttpUrl
import okhttp3.logging.HttpLoggingInterceptor


interface APIServices {

    companion object {
        val BASE_URL = "http://api.giphy.com/v1/gifs/"
        val API_KEY = "ek7rK0Eh2sbmBGwOFVsFBe93Stt4xQTB"

        fun create(): APIServices {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .client(getOkHttpBuilder().build())
                    .build()

            return retrofit.create(APIServices::class.java)
        }

        private fun getOkHttpBuilder(): OkHttpClient.Builder {
            val logging = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            return OkHttpClient.Builder()
                    .addInterceptor({ chain ->
                        val original = chain.request()
                        val originalHttpUrl = original.url()
                        val url = originalHttpUrl.newBuilder()
                                .addQueryParameter("api_key", API_KEY)
                                .build()
                        val requestBuilder = original
                                .newBuilder()
                                .url(url)
                        chain.proceed(requestBuilder.build())
                    })
                    .addInterceptor(logging)
        }
    }

    @GET("search")
    fun searchGifs(@Query("q") query: String): Observable<GiphyResponse>

}