package com.matrixhive.localizationlib.framework

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.matrixhive.localizationlib.LocalizationApplication
import com.matrixhive.localizationlib.Utils
import com.matrixhive.localizationlib.network.ApiUrls
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {



    val networkModuleDi = module {

        single {
            val interceptor = Interceptor { chain ->
                val originalResponse = chain.proceed(chain.request())
                if (Utils.isConnected(LocalizationApplication.applicationContext())) {
                    val maxAge = 60
                    return@Interceptor originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=$maxAge").build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28
                    return@Interceptor originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }
            }


            val okHttpClient: OkHttpClient =
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(StethoInterceptor())
                    .build()



            Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiUrls.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        factory {
            (get<Retrofit>()).create<ApiInterface>(
                ApiInterface::class.java
            )
        }
    }


}

// Todo add your api services here and remove suspend keyword if you dont want to use coroutines
interface ApiInterface {
    /*@GET("photos/")
    suspend fun getListData(@Query("client_id") clientId: String, @Query("page") page: Int,
                            @Query("per_page") pageSize: Int) : Response<List<SplashResponse>>

    @GET("collections/")
    suspend fun getCollectionData(@Query("client_id") clientId: String, @Query("page") page: Int,
                        @Query("per_page") pageSize: Int) : Response<List<CollectionResponse>>*/
}