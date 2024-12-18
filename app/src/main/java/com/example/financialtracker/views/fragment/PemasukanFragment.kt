package com.example.financialtracker.views.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financialtracker.databinding.FragmentPemasukanBinding
import com.example.financialtracker.viewmodels.TransactionViewModel
import com.example.financialtracker.views.DetailActivity
import com.example.financialtracker.views.adapter.TransactionAdapter

class PemasukanFragment : Fragment() {

    private lateinit var binding: FragmentPemasukanBinding
    private val transactionViewModel: TransactionViewModel by viewModels()
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPemasukanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSpinner()
    }

    private fun setupRecyclerView() {
        transactionAdapter = TransactionAdapter(emptyList()) { transaction ->
            val intent = Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_TRANSACTION, transaction)
            }
            startActivity(intent)
        }

        binding.recyclerViewPemasukan.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = transactionAdapter
        }
    }

    private fun setupSpinner() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedMonth = if (position == 0) "" else String.format("%02d", position)


                if (selectedMonth.isEmpty()) {
                    // Jika tidak ada filter bulan, ambil semua transaksi
                    transactionViewModel.getAllTransactions(requireContext()).observe(viewLifecycleOwner) { transactions ->
                        transactionAdapter.updateData(transactions)
                    }
                } else {
                    // Jika bulan dipilih, ambil transaksi berdasarkan bulan
                    transactionViewModel.getTransactionsByMonth(requireContext(), selectedMonth)
                        .observe(viewLifecycleOwner) { transactions ->
                            transactionAdapter.updateData(transactions)
                        }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Kosongkan RecyclerView jika tidak ada pilihan
                transactionAdapter.updateData(emptyList())
            }
        }
    }
}
