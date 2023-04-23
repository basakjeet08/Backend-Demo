package com.anirban.backenddemo.model

/**
 * This is the structure of the Bank Data
 *
 * @param accountNumber This is the account Number of the bank data
 * @param trust This is the percent of trust given to the bank
 * @param transactionFee This is the transaction Fees inside the Account
 */
data class BankData(
    val accountNumber: String,
    val trust: Double,
    val transactionFee: Int
)