package com.example.kinwaetest.ui.bottomNavigation.wallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinwaetest.R
import com.example.kinwaetest.databinding.FragmentWalletBinding
import com.example.kinwaetest.ui.bottomNavigation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WalletFragment : Fragment(R.layout.fragment_wallet) {
    private lateinit var bind: FragmentWalletBinding
    private val walletViewModel: WalletViewModel by viewModels()
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var adapter: WalletTransactionAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentWalletBinding.inflate(layoutInflater)
        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WalletFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = WalletTransactionAdapter() {
            if (findNavController().currentDestination?.id == R.id.walletFragment)
                WalletFragmentDirections.actionWalletFragmentToWalletTransactionDetailsFragment(it)
        }

        lifecycleScope.launch {
            walletViewModel.getTransactionList().collectLatest {
                adapter.submitList(it)
            }
        }

        bind.recyclerView.layoutManager = LinearLayoutManager(activity)
        bind.recyclerView.adapter = adapter

        mainViewModel.userName.observe(viewLifecycleOwner, Observer {
            bind.header.fullNameTextView.text = it
        })

        bind.loantextView.setOnClickListener {
            //isWalletSelected = true
            //it.background = AppCompatResources.getDrawable(requireActivity(), R.drawable.wallet_indicator)
            //bind.loantextView.setTextColor(resources.getColor(R.color.primary_color))
        }

        bind.viewAllTextView.setOnClickListener {
            if (findNavController().currentDestination?.id == R.id.walletFragment)
                findNavController().navigate(R.id.action_walletFragment_to_walletTransactionsFragment)
        }

    }
}
