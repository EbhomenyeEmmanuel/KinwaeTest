package com.example.kinwaetest.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kinwaetest.R
import com.example.kinwaetest.domain.model.onboarding.ScreenItem
import com.example.kinwaetest.databinding.LayoutScreenBinding

class IntroViewPagerAdapter(
    var listScreens: List<ScreenItem>
) : RecyclerView.Adapter<IntroViewPagerAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return listScreens.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val bind: LayoutScreenBinding =
            LayoutScreenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bind)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val screenItem = listScreens[position]
        holder.setData(screenItem)
    }

    inner class MyViewHolder(var binding: LayoutScreenBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun setData(screenItem: ScreenItem) {
            Glide.with(binding.root.context)
                .load(screenItem.screenImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.imageView);
            binding.titleTextView.text = screenItem.title
            binding.descriptionTextView.text = screenItem.description
            binding.imageView.setImageResource(screenItem.screenImage)
        }
    }
}