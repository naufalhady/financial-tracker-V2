package com.example.financialtracker.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.financialtracker.model.Transaction
import com.example.financialtracker.room.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionRepository {
    companion object {
        private var appDatabase: AppDatabase? = null

        fun initDb(context: Context): AppDatabase {
            return AppDatabase.getDatabase(context)
        }

        fun getAllTrc(context: Context): LiveData<List<Transaction>>? {
            appDatabase = initDb(context)
            return appDatabase?.transactionDao()?.getAllTransactions()
        }

        fun insertTrc(context: Context, transaction: Transaction) {
            appDatabase = initDb(context)
            CoroutineScope(Dispatchers.IO).launch {
                appDatabase!!.transactionDao().insertTransaction(transaction)
            }
        }

        fun updateTransaction(context: Context, transaction: Transaction){
            appDatabase = initDb(context)
            CoroutineScope(Dispatchers.IO).launch {
                appDatabase!!.transactionDao().updateTransaction(transaction)
            }
        }

        fun deleteTransaction(context: Context, transaction: Transaction) {
            appDatabase = initDb(context)
            CoroutineScope(Dispatchers.IO).launch {
                appDatabase!!.transactionDao().deleteTransaction(transaction)
            }
        }
        // Fungsi untuk menghapus semua transaksi
        fun deleteAllTransactions(context: Context) {
            appDatabase = initDb(context)
            CoroutineScope(Dispatchers.IO).launch {
                appDatabase!!.transactionDao().deleteAllTransactions()
            }
        }

        // Metode untuk mendapatkan daftar transaksi berdasarkan jenis transaksi
        fun getTransactionsByType(context: Context, type: String): LiveData<List<Transaction>> {
            appDatabase = initDb(context)
            val transactions = appDatabase!!.transactionDao().getTransactionsByType(type)
            transactions.observeForever {
                Log.d("TransactionRepository", "Transactions of type $type: $it")
            }
            return transactions
        }

        // Fungsi untuk mendapatkan transaksi pendapatan
        fun getIncomeTransactions(context: Context): LiveData<List<Transaction>> {
            return getTransactionsByType(context, "Pemasukan")
        }

        // Fungsi untuk mendapatkan transaksi pengeluaran
        fun getExpenseTransactions(context: Context): LiveData<List<Transaction>> {
            return getTransactionsByType(context, "Pengeluaran")
        }

        fun getAllTransactions(context: Context): LiveData<List<Transaction>> {
            val transactionDao = AppDatabase.getDatabase(context).transactionDao()
            return transactionDao.getAllTransactions()
        }

        private fun getDatabase(context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
        }

        fun getTransactionsByMonth(context: Context, month: String): LiveData<List<Transaction>> {
            val transactionDao = AppDatabase.getDatabase(context).transactionDao()
            return transactionDao.getTransactionsByMonth(month)
        }
    }
}
