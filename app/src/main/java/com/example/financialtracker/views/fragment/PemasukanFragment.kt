package com.example.financialtracker.views.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financialtracker.R
import com.example.financialtracker.databinding.FragmentPemasukanBinding
import com.example.financialtracker.model.Transaction
import com.example.financialtracker.utility.indonesiaRupiah
import com.example.financialtracker.viewmodels.TransactionViewModel
import com.example.financialtracker.views.DetailActivity
import com.example.financialtracker.views.adapter.TransactionAdapter // Tambahkan baris ini untuk mengimport TransactionAdapter yang benar

class PemasukanFragment : Fragment() {

    private lateinit var binding: FragmentPemasukanBinding
    private val transactionViewModel: TransactionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPemasukanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the status bar color
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_dark)

        transactionViewModel.getIncomeTransactions(this.requireContext())?.observe(viewLifecycleOwner) { transactions ->
            Log.d("PemasukanFragment", "Observed transactions: $transactions")
            if (transactions.isNullOrEmpty()) {
                Toast.makeText(this.requireContext(), "Tidak ada data transaksi!", Toast.LENGTH_SHORT).show()
            } else {
                binding.transactionRv.apply {
                    layoutManager = LinearLayoutManager(this@PemasukanFragment.requireContext())
                    adapter = TransactionAdapter(transactions) { transaction ->
                        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                            putExtra(DetailActivity.EXTRA_TRANSACTION, transaction)
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }
}
