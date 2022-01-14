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
import com.example.kinwaetest.databinding.FragmentSignUpBinding
import com.example.kinwaetest.domain.model.auth.SignUpAuthResult
import com.example.kinwaetest.utils.UtilLoader
import com.example.kinwaetest.utils.afterTextChanged
import com.example.kinwaetest.utils.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var bind: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var loader: UtilLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentSignUpBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loader = UtilLoader(requireActivity())
    }

    companion object {
        fun newInstance() = SignUpFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.signUpFormState.observe(viewLifecycleOwner, Observer {
            val signUpFormState: Int = it ?: return@Observer
            when (signUpFormState) {
                1 -> bind.fullNameEditText.error = getString(R.string.invalid_username)
                2 -> bind.emailAddressEditText.error = getString(R.string.invalid_email)
                3 -> bind.phoneNumberEditText.error = getString(R.string.invalid_phone_number)
                4 -> bind.passwordEditText.error =
                    getString(R.string.invalid_password)
                5 -> {
                    viewModel.signUp(
                        username = bind.fullNameEditText.text.toString(),
                        email = bind.emailAddressEditText.text.toString(),
                        phoneNumber = bind.phoneNumberEditText.text.toString(),
                        password = bind.passwordEditText.text.toString()
                    )
                }
            }
        })

        viewModel.signUpResultLogIn.observe(viewLifecycleOwner, Observer {
            val signUpResult = it ?: return@Observer
            when (signUpResult) {
                is SignUpAuthResult.Success -> {
                    loader.dismiss()
                    //completeSignUp(signUpResult.successMessage)
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                }
                is SignUpAuthResult.Error -> {
                    loader.dismiss()
                    showSignUpFailed(signUpResult.error)
                }
                is SignUpAuthResult.Loading -> {
                    loader.show()
                }
            }

        })

        bind.fullNameEditText.afterTextChanged {
            viewModel.formState.fullName = it
        }

        bind.emailAddressEditText.afterTextChanged {
            viewModel.formState.userEmail = it
        }

        bind.phoneNumberEditText.afterTextChanged {
            viewModel.formState.phoneNumber = it
        }

        bind.passwordEditText.afterTextChanged {
            viewModel.formState.password = it
        }

        bind.signUpButton.setOnClickListener {
            viewModel.signUpDataChanged()
        }

        bind.loginTextView.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.signUpFragment)
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        bind.backImageButton.setOnClickListener {
            //Navigate to intro activity
            if (findNavController().currentDestination?.id == R.id.signUpFragment)
                findNavController().navigate(R.id.action_signUpFragment_to_introFragment)
        }
    }

    private fun completeSignUp(successMessage: String) {
        if (findNavController().currentDestination?.id == R.id.signUpFragment)
            findNavController().navigate(
                SignUpFragmentDirections.actionSignUpFragmentToSignUpAlertDialogFragment(
                    successMessage
                )
            )
    }

    private fun showSignUpFailed(errorString: String) {
        activity?.shortToast(errorString)
    }

}