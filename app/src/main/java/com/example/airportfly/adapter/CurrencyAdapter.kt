package com.example.airportfly.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.airportfly.R
import com.example.airportfly.databinding.CurrencyItemBinding
import com.example.airportfly.util.getCurrencyFromName

class CurrencyAdapter :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private data class Currency(
        val name: String,
        val rate: Double
    )

    private val items = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.name == newItem.name
        }

    })

    fun submitList(datas: Map<String, Double>) {
        items.submitList(datas.map { Currency(it.key, it.value) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CurrencyItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(private val binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val data = items.currentList[position]
            val currency = getCurrencyFromName(data.name)

            binding.imgCurrency.setImageResource(currency?.iconRes ?: R.drawable.ic_unknown)
            binding.tvTitle.text = data.name
            binding.tvRate.text = String.format("%.2f", data.rate)
        }
    }
}