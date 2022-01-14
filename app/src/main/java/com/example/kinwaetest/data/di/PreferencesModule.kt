package com.example.kinwaetest.data.di

import com.example.kinwaetest.data.preferences.AuthPreferences
import com.example.kinwaetest.data.preferences.KinwaeApiPreferences
import com.example.kinwaetest.data.preferences.KinwaeAuthPreferences
import com.example.kinwaetest.data.preferences.ApiPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    abstract fun provideKinsWaeApiPreferences(apiPreferences: KinwaeApiPreferences): ApiPreferences

    @Binds
    abstract fun provideKinsWaeAuthPreferences(apiPreferences: KinwaeAuthPreferences): AuthPreferences

}