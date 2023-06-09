package com.anirban.backenddemo.datasource

import com.anirban.backenddemo.model.BankData

interface BankDataSource {

    // Function which retrieves the Bank Details Data
    fun retrieveData(): List<BankData>

    // Function which returns the bank Data which has the given accountNumber
    fun retrieveIndividualBank(accountNumber: String): BankData

    // This function adds a Bank Data to the Database
    fun addBankData(bankData: BankData): BankData

    // This function updates a Bank Data in the database
    fun updateBankData(bankData: BankData): BankData

    // This function deletes the bank Data in the database
    fun deleteBankData(accountNumber: String)
}