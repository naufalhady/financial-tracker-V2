package com.example.financialtracker.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.financialtracker.R
import com.example.financialtracker.databinding.ItemTransactionLayoutBinding
import com.example.financialtracker.model.Transaction
import com.example.financialtracker.utility.indonesiaRupiah

class TransactionAdapter(
    private val allTrc: List<Transaction> = ArrayList(),
    private val onItemClick: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TrcVH>() {

    inner class TrcVH(val binding: ItemTransactionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction) {
            binding.apply {
                transactionName.text = item.title
                transactionCategory.text = item.tag

                when (item.transactionType) {
                    "Pemasukan" -> {
                        transactionAmount.setTextColor(
                            ContextCompat.getColor(
                                transactionAmount.context,
                                R.color.income
                            )
                        )
                        transactionAmount.text = "+ ".plus(indonesiaRupiah(item.amount))
                    }
                    "Pengeluaran" -> {
                        transactionAmount.setTextColor(
                            ContextCompat.getColor(
                                transactionAmount.context,
                                R.color.expense
                            )
                        )
                        transactionAmount.text = "- ".plus(indonesiaRupiah(item.amount))
                    }
                }
                when (item.tag) {
                    "Kebutuhan Sehari-hari" -> transactionIconView.setImageResource(R.drawable.ic_food)
                    "Tabungan dan Investasi" -> transactionIconView.setImageResource(R.drawable.ic_savings)
                    "Gaya hidup" -> transactionIconView.setImageResource(R.drawable.ic_lifestyle)
                    "Hal lainnya" -> transactionIconView.setImageResource(R.drawable.ic_others)
                    else -> transactionIconView.setImageResource(R.drawable.ic_others)
                }

                root.setOnClickListener {
                    onItemClick(item)
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrcVH {
        val binding = ItemTransactionLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrcVH(binding)
    }

    override fun onBindViewHolder(holder: TrcVH, position: Int) {
        holder.bind(allTrc[position])
    }

    override fun getItemCount(): Int = allTrc.size
}




//With danu
//class TransactionAdapter(
//    private val allTrc: List<Transaction> = ArrayList<Transaction>())
//    : RecyclerView.Adapter<TransactionAdapter.TrcVH>(){
//
//        inner class TrcVH(val binding: ItemTransactionLayoutBinding):
//                RecyclerView.ViewHolder(binding.root)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrcVH {
//        val binding = ItemTransactionLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false)
//        return TrcVH(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return allTrc.size
//    }
//
//    override fun onBindViewHolder(holder: TrcVH, position: Int) {
//        holder.binding.apply {
//            val item = allTrc[position]
//            Log.d("Data",item.title)
//
//            transactionName.text = item.title
//            transactionCategory.text = item.tag
//
//            when (item.transactionType) {
//                "Income" -> {
//                    transactionAmount.setTextColor(
//                        ContextCompat.getColor(
//                            transactionAmount.context,
//                            R.color.income
//                        )
//                    )
//
//                    transactionAmount.text = "+ ".plus(indonesiaRupiah(item.amount))
//                }
//                "Expense" -> {
//                    transactionAmount.setTextColor(
//                        ContextCompat.getColor(
//                            transactionAmount.context,
//                            R.color.expense
//                        )
//                    )
//                    transactionAmount.text = "- ".plus(indonesiaRupiah(item.amount))
//                }
//            }
//            when (item.tag) {
//                "Essential Needs" -> {
//                    transactionIconView.setImageResource(R.drawable.ic_food)
//                }
//                "Savings and Investments" -> {
//                    transactionIconView.setImageResource(R.drawable.ic_savings)
//                }
//                "Lifestyle" -> {
//                    transactionIconView.setImageResource(R.drawable.ic_lifestyle)
//                }
//                "Miscellaneous" -> {
//                    transactionIconView.setImageResource(R.drawable.ic_others)
//                }
//                else -> {
//                    transactionIconView.setImageResource(R.drawable.ic_others)
//                }
//            }
//        }
//    }
//    }
