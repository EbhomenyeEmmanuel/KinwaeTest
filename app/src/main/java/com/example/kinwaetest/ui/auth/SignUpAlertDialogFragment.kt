package com.example.kinwaetest.ui.auth

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kinwaetest.R
import com.example.kinwaetest.databinding.FragmentSignUpAlertDialogBinding

class SignUpAlertDialogFragment : DialogFragment(R.layout.fragment_sign_up_alert_dialog) {
    private lateinit var binding: FragmentSignUpAlertDialogBinding
    private val args: SignUpAlertDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentSignUpAlertDialogBinding.inflate(layoutInflater)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
            builder.setCancelable(false)
            binding.messageTextView.text = args.successfulSignUpMessage
            binding.button.setOnClickListener {
                if (findNavController().currentDestination?.id == R.id.signUpAlertDialogFragment)
                    findNavController().navigate(R.id.action_signUpAlertDialogFragment_to_loginFragment)
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}