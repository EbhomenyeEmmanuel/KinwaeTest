package com.example.kinwaetest.ui.bottomNavigation

import androidx.lifecycle.*
import com.example.kinwaetest.domain.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    init {
        displayUserData()
    }

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private fun displayUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            Transformations.map(mainRepository.getUserFromDb()!!) { user ->
                with(user) {
                    _userName.postValue("$firstName $lastName")
                }
            }
        }
    }
}