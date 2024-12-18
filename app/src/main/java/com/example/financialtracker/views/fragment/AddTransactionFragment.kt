package com.example.financialtracker.views.fragment

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financialtracker.R
import com.example.financialtracker.databinding.FragmentAddTransactionBinding
import com.example.financialtracker.model.Transaction
import com.example.financialtracker.viewmodels.TransactionViewModel
import com.example.financialtracker.views.adapter.CustomSpinnerAdapter

class AddTransactionFragment : Fragment() {
    private lateinit var binding: FragmentAddTransactionBinding
    private val transactionModel by viewModels<TransactionViewModel>()
    var trcTag: String? = null
    var trcType: String? = null
    private var isTagSpinnerInitialized = false
    private var isTypeSpinnerInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val arrTag = resources.getStringArray(R.array.tag)
        val adapter = CustomSpinnerAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arrTag
        )
        binding.addTransactionLayout.tag.adapter = adapter

        val arrTrcType = resources.getStringArray(R.array.TrcType)
        val typeAdapter = CustomSpinnerAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            arrTrcType
        )
        binding.addTransactionLayout.transactionType.adapter = typeAdapter

        binding.addTransactionLayout.tag.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (isTagSpinnerInitialized) {
                        if (position == 0) {
                            Toast.makeText(requireContext(), "Pilih kategori yang sesuai", Toast.LENGTH_SHORT).show()
                        } else {
                            trcTag = arrTag[position]
                        }
                    } else {
                        isTagSpinnerInitialized = true
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing
                }
            }

        binding.addTransactionLayout.transactionType.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    if (isTypeSpinnerInitialized) {
                        if (position == 0) {
                            Toast.makeText(requireContext(), "Pilih kategori yang sesuai", Toast.LENGTH_SHORT).show()
                        } else {
                            trcType = arrTrcType[position]
                        }
                    } else {
                        isTypeSpinnerInitialized = true
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Do nothing
                }
            }

        binding.addTransactionLayout.etWhen.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnSave.setOnClickListener {
            if (validateInputs()) {
                try {
                    val data = Transaction(
                        title = binding.addTransactionLayout.etTitle.text.toString(),
                        amount = binding.addTransactionLayout.etAmount.text.toString().toDouble(),
                        date = binding.addTransactionLayout.etWhen.text.toString(),
                        note = binding.addTransactionLayout.etNote.text.toString(),
                        tag = trcTag.toString(),
                        transactionType = trcType.toString()
                    )
                    transactionModel.insertTrc(requireContext(), data)
                    Toast.makeText(requireContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                    clearFields()
                } catch (ex: Exception) {
                    Toast.makeText(requireContext(), ex.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        with(binding.addTransactionLayout) {
            return when {
                etTitle.text.isNullOrEmpty() -> {
                    etTitle.error = "Judul wajib diisi"
                    false
                }
                etAmount.text.isNullOrEmpty() -> {
                    etAmount.error = "Jumlah wajib diisi"
                    false
                }
                etWhen.text.isNullOrEmpty() -> {
                    etWhen.error = "Tanggal wajib diisi"
                    false
                }
                etNote.text.isNullOrEmpty() -> {
                    etNote.error = "Catatan wajib diisi"
                    false
                }
                trcTag.isNullOrEmpty() -> {
                    Toast.makeText(requireContext(), "Tag wajib diisi", Toast.LENGTH_SHORT).show()
                    false
                }
                trcType.isNullOrEmpty() -> {
                    Toast.makeText(requireContext(), "Jenis transaksi wajib diisi", Toast.LENGTH_SHORT).show()
                    false
                }
                else -> true
            }
        }
    }

    private fun clearFields() {
        with(binding.addTransactionLayout) {
            etTitle.text = null
            etAmount.text = null
            etWhen.text = null
            etNote.text = null
        }
        trcTag = null
        trcType = null
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                binding.addTransactionLayout.etWhen.setText(
                    String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                )
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }
}
