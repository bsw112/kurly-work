package com.manta.kurly_work.di

import android.content.Context
import android.content.SharedPreferences
import com.manta.kurly_work.data.local.AppPreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext applicationContext: Context): SharedPreferences =
        applicationContext.getSharedPreferences(
            "${applicationContext.packageName}.PREFERENCE_FILE_KEY",
            Context.MODE_PRIVATE
        )

    @Provides
    @Singleton
    fun provideAppPreference(sharedPreferences: SharedPreferences): AppPreference =
        AppPreference(sharedPreferences)

}