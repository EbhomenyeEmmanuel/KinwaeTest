package com.example.kinwaetest.ui.auth

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinwaetest.domain.model.auth.SignUpAuthResult
import com.example.kinwaetest.domain.repositories.SignUpRepository
import com.example.kinwaetest.domain.model.auth.SignUpFormState
import com.example.kinwaetest.domain.model.auth.SignedInUserResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository,
    var formState: SignUpFormState
) : ViewModel() {

    val TAG = this::class.java.simpleName

    private val _signUpForm = MutableLiveData<Int>()
    val signUpFormState: LiveData<Int> get() = _signUpForm

    private val _signUpResult = MutableLiveData<SignUpAuthResult>()
    val signUpResultLogIn: LiveData<SignUpAuthResult> get() = _signUpResult

    fun signUp(username: String, email: String, phoneNumber: String, password: String) {
        _signUpResult.postValue(SignUpAuthResult.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            try {
                val data: SignedInUserResults =
                    signUpRepository.signUp(fullname = username, email, password)
                _signUpResult.postValue(SignUpAuthResult.Success(data.successMessage))
                Log.i(TAG, "signUp: Success")
            } catch (e: Exception) {
                Log.e(TAG, e.message ?: "An Error occurred!")
                _signUpResult.postValue(
                    SignUpAuthResult.Error(
                        error = e.message ?: "An Error occurred!"
                    )
                )
            }
        }
    }

    fun signUpDataChanged() {
        _signUpForm.value = isValidData(formState)
    }

    private fun isValidData(signUpFormState: SignUpFormState): Int {
        return if (!isUserNameValid(signUpFormState.fullName)) {
            1
        } else if (!isUserEmailValid(signUpFormState.userEmail)) {
            2
        } else if (!isPhoneNumberValid(signUpFormState.phoneNumber)) {
            3
        } else if (!isPasswordValid(signUpFormState.password)) {
            4
        } else {
            5
        }
    }

    private fun isUserNameValid(userName: String): Boolean {
        return userName.length > 5
    }

    private fun isUserEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotBlank()
    }

    private fun isPhoneNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.startsWith('0') && phoneNumber.length == 11
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

}