package dev.hyuwah.silk.common.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.hyuwah.silk.BuildConfig
import dev.hyuwah.silk.common.data.local.LocalPreferences
import dev.hyuwah.silk.common.data.local.LocalPreferencesImpl
import dev.hyuwah.silk.common.data.remote.ReqResService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
class CommonModule {

    @Provides
    fun provideAppContext(@ApplicationContext context: Context) = context

    @Provides
    fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                })
            }
            addInterceptor(ChuckerInterceptor(context.applicationContext))
        }.build()
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Named(Name.RETROFIT_REQRES)
    fun provideReqResRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideReqResService(@Named(Name.RETROFIT_REQRES) retrofit: Retrofit): ReqResService {
        return retrofit.create(ReqResService::class.java)
    }

    @Provides
    fun provideLocalPreferences(context: Context): LocalPreferences {
        return LocalPreferencesImpl(
            context.getSharedPreferences(
                "silk_preferences",
                Context.MODE_PRIVATE
            )
        )
    }

    object Name {
        const val RETROFIT_REQRES = "retrofit_reqres"
    }
}