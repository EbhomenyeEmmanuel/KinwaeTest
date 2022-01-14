package com.example.kinwaetest.ui.bottomNavigation.wallet

import androidx.lifecycle.*
import com.example.kinwaetest.domain.model.wallet.Transaction
import com.example.kinwaetest.domain.repositories.MainRepository
import com.example.kinwaetest.domain.repositories.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(private val walletRepository: WalletRepository) :
    ViewModel() {

    init {
        viewModelScope.launch {
            walletRepository.initTransactionDetails()
        }
    }

    suspend fun getTransactionList(): Flow<List<Transaction>> =
        walletRepository.getTransactionsFromDb()

}