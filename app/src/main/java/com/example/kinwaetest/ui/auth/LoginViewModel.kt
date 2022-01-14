package com.example.kinwaetest.ui.auth

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.*
import com.example.kinwaetest.domain.model.auth.*
import com.example.kinwaetest.domain.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    var formState: LoginFormState
) : ViewModel() {

    val TAG = this::class.java.simpleName

    private val _loginForm = MutableLiveData<Int>()
    val loginFormState: LiveData<Int> get() = _loginForm

    private val _loginResult = MutableLiveData<LogInAuthResult>()
    val loginResultLogIn: LiveData<LogInAuthResult> get() = _loginResult

    fun login(email: String, password: String) {
        _loginResult.postValue(LogInAuthResult.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            try {
                val data = loginRepository.login(email, password)
                    loginRepository.saveTokenInDb(data.loggedInUser.accessToken)
                    loginRepository.saveLoggedUserInDb(data.loggedInUser)
                    _loginResult.postValue(LogInAuthResult.Success(LoggedInUserView(data.loggedInUser.firstName)))
                Log.i(TAG, "login: Success")
            } catch (e: Exception) {
                Log.e(TAG, e.message?:"An Error occurred!")
                _loginResult.postValue(LogInAuthResult.Error(error = e.message?:"An Error occurred!"))
            }
        }
    }

    fun loginDataChanged() {
        _loginForm.value = isValidData(formState)
    }

    fun rememberUserForLogin() {
        Transformations.map(loginResultLogIn) {
            Log.i(TAG, "rememberUserForLogin: Result is $it")
            if (it == LogInAuthResult.Success()) {
                viewModelScope.launch {
                    loginRepository.saveLoggedUserForAuth()
                }
            }
        }
    }

    private fun isValidData(loginFormState: LoginFormState): Int {
        return if (!isUserEmailValid(loginFormState.userEmail)) {
            1
        } else if (!isPasswordValid(loginFormState.password)) {
            2
        } else {
            3
        }
    }

    private fun isUserEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotBlank()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    //Check if user is logged in
    private fun isUserLoggedIn(): Boolean {
        return loginRepository.isLoggedIn
    }
}