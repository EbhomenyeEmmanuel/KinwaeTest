package com.example.kinwaetest.ui.bottomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kinwaetest.R
import com.example.kinwaetest.databinding.FragmentProfileBinding
import com.example.kinwaetest.databinding.FragmentWalletBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var bind: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentProfileBinding.inflate(layoutInflater)
        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ProfileFragment()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}