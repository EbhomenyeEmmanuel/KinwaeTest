package com.example.kinwaetest.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.kinwaetest.R
import com.example.kinwaetest.databinding.LoginFragmentBinding
import com.example.kinwaetest.domain.model.auth.LogInAuthResult
import com.example.kinwaetest.domain.model.auth.LoggedInUserView
import com.example.kinwaetest.utils.UtilLoader
import com.example.kinwaetest.utils.afterTextChanged
import com.example.kinwaetest.utils.longToast
import com.example.kinwaetest.utils.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.login_fragment) {

    private lateinit var bind: LoginFragmentBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var loader: UtilLoader

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = LoginFragmentBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loader = UtilLoader(requireActivity())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val signUpFormState: Int = it ?: return@Observer
            when (signUpFormState) {
                1 -> bind.emailAddressEditText.error =
                    getString(R.string.invalid_email)
                2 -> bind.passwordEditText.error =
                    getString(R.string.invalid_password)
                3 -> {
                    viewModel.login(
                        email = bind.emailAddressEditText.text.toString(),
                        password = bind.passwordEditText.text.toString()
                    )
                }
            }
        })

        viewModel.loginResultLogIn.observe(viewLifecycleOwner, Observer {
            val signUpResult = it ?: return@Observer

            when (signUpResult) {
                is LogInAuthResult.Success -> {
                    loader.dismiss()
                    signUpResult.success?.let { it1 ->
                        updateUiWithUser(it1)
                        goToHome()
                    }
                    //Navigate to Main activity
                }
                is LogInAuthResult.Error -> {
                    loader.dismiss()
                    showLoginFailed(signUpResult.error)
                }
                is LogInAuthResult.Loading -> {
                    loader.show()
                }
            }
        })

        bind.emailAddressEditText.afterTextChanged {
            viewModel.formState.userEmail = it
        }

        bind.passwordEditText.afterTextChanged {
            viewModel.formState.password = it
        }

        bind.createAccountButton.setOnClickListener {
            viewModel.loginDataChanged()
        }

        bind.rememberMeCheckBox.setOnClickListener {
            viewModel.rememberUserForLogin()
        }

        bind.backImageButton.setOnClickListener {
            //Navigate to intro activity
            if (findNavController().currentDestination?.id == R.id.loginFragment)
                findNavController().navigate(R.id.action_loginFragment_to_introFragment)
        }

    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        activity?.longToast("$welcome $displayName")
    }

    private fun showLoginFailed(errorString: String) {
        activity?.shortToast(errorString)
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
    }
}