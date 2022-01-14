package com.example.kinwaetest.data.di

import com.example.kinwaetest.domain.model.auth.LoginFormState
import com.example.kinwaetest.domain.model.auth.SignUpFormState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object FormStateModule {

    @ViewModelScoped
    @Provides
    fun getLoginState (): LoginFormState = LoginFormState("", "")

    @ViewModelScoped
    @Provides
    fun getSignUpState (): SignUpFormState = SignUpFormState("", "", "", "")
}