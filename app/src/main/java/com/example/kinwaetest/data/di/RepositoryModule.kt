package com.example.kinwaetest.data.di

import com.example.kinwaetest.data.LoginRepositoryImpl
import com.example.kinwaetest.data.MainRepositoryImpl
import com.example.kinwaetest.data.SignUpRepositoryImpl
import com.example.kinwaetest.data.WalletRepositoryImpl
import com.example.kinwaetest.domain.repositories.LoginRepository
import com.example.kinwaetest.domain.repositories.MainRepository
import com.example.kinwaetest.domain.repositories.SignUpRepository
import com.example.kinwaetest.domain.repositories.WalletRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository

    @Binds
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun provideMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun provideWalletRepository(walletRepositoryImpl: WalletRepositoryImpl): WalletRepository
}
