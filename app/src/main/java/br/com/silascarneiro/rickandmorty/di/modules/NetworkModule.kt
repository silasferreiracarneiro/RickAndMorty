package br.com.silascarneiro.rickandmorty.di.modules

import android.content.Context
import br.com.silascarneiro.rickandmorty.api.Api
import br.com.silascarneiro.rickandmorty.config.interceptor.CacheInterceptor
import br.com.silascarneiro.rickandmorty.config.interceptor.ErrorInterceptor
import br.com.silascarneiro.rickandmorty.config.interceptor.ForceCacheInterceptor
import br.com.silascarneiro.rickandmorty.utils.Constants.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class NetworkModule @Inject constructor(val context: Context) {

    @Provides
    @Singleton
    fun provideApi() = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client())
        .build()
        .create(Api::class.java)

    private fun client() =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(ErrorInterceptor())
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(ForceCacheInterceptor(context))
            .build()
}