package com.example.financialtracker.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.financialtracker.model.Transaction

@Dao
interface TransactionDao {

    // used to insert new transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(transaction: Transaction)

    // used to update existing transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTransaction(transaction: Transaction)

    // used to delete transaction
    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    // get all saved transaction list
    @Query("SELECT * FROM transactions ORDER BY createdAt DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>

    // get all income or expense list by transaction type param
    @Query("SELECT * FROM transactions WHERE transactionType = :transactionType ORDER BY createdAt DESC")
    fun getTransactionsByType(transactionType: String): LiveData<List<Transaction>>

    // Menambahkan query untuk menghapus semua transaksi
    @Query("DELETE FROM transactions")
    suspend fun deleteAllTransactions()  // Query untuk menghapus semua transaksi

    // Query untuk mendapatkan transaksi berdasarkan rentang tanggal
    @Query("SELECT * FROM transactions WHERE createdAt BETWEEN :startDate AND :endDate ORDER BY createdAt DESC")
    fun getTransactionsByDateRange(startDate: Long, endDate: Long): LiveData<List<Transaction>>
}
