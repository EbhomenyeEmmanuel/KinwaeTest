package com.example.kinwaetest.ui.bottomNavigation.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinwaetest.R
import com.example.kinwaetest.databinding.FragmentWalletTransactionsBinding
import com.example.kinwaetest.utils.shortToast
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class WalletTransactionsFragment : Fragment(R.layout.fragment_wallet_transactions) {
    private lateinit var bind: FragmentWalletTransactionsBinding
    var isWalletSelected: Boolean = true
    private val walletViewModel: WalletViewModel by viewModels()
    private lateinit var adapter: WalletTransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentWalletTransactionsBinding.inflate(layoutInflater)
        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WalletTransactionsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = WalletTransactionAdapter() {
            if (findNavController().currentDestination?.id == R.id.walletTransactionsFragment)
                WalletTransactionsFragmentDirections.actionWalletTransactionsFragmentToWalletTransactionDetailsFragment(
                    it
                )
        }

        lifecycleScope.launch {
            walletViewModel.getTransactionList().collectLatest {
                adapter.submitList(it)
            }
        }

        bind.recyclerView.layoutManager = LinearLayoutManager(activity)
        bind.recyclerView.adapter = adapter
        bind.filterButton.setOnClickListener {
            activity?.shortToast("Filter")
        }

        bind.backImageButton.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.walletTransactionsFragment)
                findNavController().navigate(R.id.action_walletTransactionsFragment_to_walletFragment)
        }

    }
}
