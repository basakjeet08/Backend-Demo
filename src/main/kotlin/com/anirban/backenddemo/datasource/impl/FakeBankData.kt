package com.anirban.backenddemo.datasource.impl

import com.anirban.backenddemo.model.BankData

/**
 * This file contains the Fake Bank Data for testing the JUnit Test Cases
 */
object FakeBankData {

    // Test Bank Data List for referring
    val bankData = mutableListOf(
        BankData(
            accountNumber = "21051880",
            trust = 86.8,
            transactionFee = 2000
        ),

        BankData(
            accountNumber = "21051881",
            trust = 98.8,
            transactionFee = 2000
        ),

        BankData(
            accountNumber = "21051882",
            trust = 34.8,
            transactionFee = 2000
        ),

        BankData(
            accountNumber = "21051883",
            trust = 56.8,
            transactionFee = 2000
        )
    )
}