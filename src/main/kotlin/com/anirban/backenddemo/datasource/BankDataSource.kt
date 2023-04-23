package com.anirban.backenddemo.datasource

import com.anirban.backenddemo.model.BankData

interface BankDataSource {

    // Function which retrieves the Bank Details Data
    fun retrieveData(): List<BankData>

    // Function which returns the bank Data which has the given accountNumber
    fun retrieveIndividualBank(accountNumber: String): BankData

    // This function adds a Bank Data to the Database
    fun addBankData(bankData: BankData): BankData
}