package me.amirkazemzade.myshatelmobilewidget.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import me.amirkazemzade.myshatelmobilewidget.data.CookieRepositoryImpl
import me.amirkazemzade.myshatelmobilewidget.data.api.AuthApi
import me.amirkazemzade.myshatelmobilewidget.data.api.PackagesApi
import me.amirkazemzade.myshatelmobilewidget.data.repositories.AuthRepositoryImpl
import me.amirkazemzade.myshatelmobilewidget.data.repositories.PackagesRepositoryImpl
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.AuthRepository
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.CookieRepository
import me.amirkazemzade.myshatelmobilewidget.domain.repositories.PackagesRepository
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

private const val USER_PREFERENCES = "my_shatel_mobile_widget_prefs"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(USER_PREFERENCES)
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()
                )
            )
            .baseUrl("https://my.shatelmobile.ir/")
            .build()

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)

    @Provides
    @Singleton
    fun providePackageApi(retrofit: Retrofit): PackagesApi =
        retrofit.create(PackagesApi::class.java)

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi): AuthRepository = AuthRepositoryImpl(authApi)

    @Provides
    @Singleton
    fun providePackagesRepository(packagesApi: PackagesApi): PackagesRepository =
        PackagesRepositoryImpl(packagesApi)

    @Provides
    @Singleton
    fun provideCookieRepository(dataStore: DataStore<Preferences>): CookieRepository =
        CookieRepositoryImpl(dataStore)
}
