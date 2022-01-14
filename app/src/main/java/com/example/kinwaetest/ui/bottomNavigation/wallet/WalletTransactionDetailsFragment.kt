package com.example.kinwaetest.ui.bottomNavigation.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kinwaetest.R
import com.example.kinwaetest.databinding.FragmentWalletTransactionDetailsBinding

class WalletTransactionDetailsFragment : Fragment(R.layout.fragment_wallet_transaction_details) {
    private lateinit var bind: FragmentWalletTransactionDetailsBinding
    //private val args: WalletTransactionDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentWalletTransactionDetailsBinding.inflate(layoutInflater)
        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WalletTransactionDetailsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.backImageButton.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.walletTransactionDetailsFragment)
                findNavController().navigate(R.id.action_walletTransactionDetailsFragment_to_walletTransactionsFragment)
        }

        bind.transactionDetailsAmountTextView
        //if (args.)
        bind.transactionDetailsTypeTextView
        bind.detailNarrationTextView
        bind.detailTransactionDateTextView
        bind.detailTransactionReferenceTextView

    }
}
