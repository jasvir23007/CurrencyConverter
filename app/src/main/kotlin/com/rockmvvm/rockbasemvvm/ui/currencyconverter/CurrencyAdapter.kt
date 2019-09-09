package com.rockmvvm.rockbasemvvm.ui.currencyconverter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rockmvvm.rockbasemvvm.BR
import com.rockmvvm.rockbasemvvm.R
import com.rockmvvm.rockbasemvvm.data.CurrencyListModel
import com.rockmvvm.rockbasemvvm.databinding.ItemConverterBinding


class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private lateinit var langList: MutableList<CurrencyListModel>
    private var amount: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemConverterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.item_converter, parent, false
        )
        val holder = ViewHolder(binding)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = langList[position]
        holder.bind(langList[position], langList.size)
        if (amount.isEmpty())
            amount = "0"
        val amountToDisplay = data.value.toDouble() * amount.toDouble()
        val conversionText = holder.tvAmount.context.getString(R.string.converted_amount)
        holder.tvAmount.text = "$conversionText $amountToDisplay"

    }


    override fun getItemCount(): Int {
        return if (::langList.isInitialized) langList.size else 0
    }

    fun updateList(postList: MutableList<CurrencyListModel>) {
        this.langList = postList
        notifyDataSetChanged()
    }

    fun updateAmount(amount: String) {
        this.amount = amount
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemConverterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var tvAmount = binding.tvConversionAmount

        fun bind(obj: CurrencyListModel, size: Int) {
            binding.setVariable(BR.model, obj)
            binding.executePendingBindings()

            // binding.tvAmont.text =
        }
    }
}