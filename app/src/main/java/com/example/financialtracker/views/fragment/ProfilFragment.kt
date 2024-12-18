package com.example.financialtracker.views.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.financialtracker.R
import com.example.financialtracker.databinding.FragmentProfilBinding
import com.example.financialtracker.viewmodels.TransactionViewModel

class ProfilFragment : Fragment() {

    private lateinit var binding: FragmentProfilBinding
    private lateinit var btnHapusSemua: Button
    private lateinit var transactionViewModel: TransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi ViewModel
        transactionViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)

        // Set warna status bar
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_dark)

        // Inisialisasi tombol Hapus Semua Data
        btnHapusSemua = binding.btnHapusSemua

        // Set listener untuk tombol hapus semua data
        btnHapusSemua.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Hapus Semua Data")
            .setMessage("Apakah Anda yakin ingin menghapus semua data transaksi?")
            .setPositiveButton("Ya") { dialog, which ->
                hapusSemuaData()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun hapusSemuaData() {
        // Panggil metode di ViewModel untuk menghapus semua transaksi
        transactionViewModel.deleteAllTransactions(requireContext())

        // Menampilkan Toast setelah penghapusan selesai
        Toast.makeText(requireContext(), "Semua data transaksi telah dihapus.", Toast.LENGTH_SHORT).show()

        // Tidak ada navigasi ke HomeFragment, cukup menampilkan Toast
    }
}
