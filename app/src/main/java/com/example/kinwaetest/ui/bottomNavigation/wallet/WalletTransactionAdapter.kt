package com.example.kinwaetest.ui.bottomNavigation.wallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kinwaetest.databinding.WalletTransactionListItemBinding
import com.example.kinwaetest.domain.model.wallet.Transaction

import java.text.SimpleDateFormat
import java.util.*

class WalletTransactionAdapter(
    private val onItemClicked: (Transaction) -> Unit
) : ListAdapter<Transaction, WalletTransactionAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Transaction> =
            object : DiffUtil.ItemCallback<Transaction>() {
                override fun areItemsTheSame(
                    oldTransaction: Transaction, newTransactionDetails: Transaction
                ): Boolean {
                    return oldTransaction.transactionId.equals(newTransactionDetails.transactionId)
                }

                override fun areContentsTheSame(
                    oldTransaction: Transaction, newTransaction: Transaction
                ): Boolean {
                    return oldTransaction === newTransaction
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val viewHolder = ViewHolder(
            WalletTransactionListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val walletTransaction = getItem(position)
        holder.bind(walletTransaction!!)
    }

    inner class ViewHolder(private var binding: WalletTransactionListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) {
            with(transaction) {
                //binding.transactionTypeIcon.setImageIcon(transactionTypeIcon)
                binding.transactionTitleTextView.text = transactionTitle
                binding.transactionTimeTextView.text =
                    SimpleDateFormat("h:mm:a").format(Date(transactionDate.toLong() * 1000))
                binding.transactionAmountTextView.text = transactionAmount
            }
        }
    }
}