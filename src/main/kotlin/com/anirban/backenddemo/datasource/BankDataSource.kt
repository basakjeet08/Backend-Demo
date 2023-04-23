package com.anirban.backenddemo.datasource

import com.anirban.backenddemo.model.BankData

interface BankDataSource {

    // Function which retrieves the Bank Details Data
    fun retrieveData(): List<BankData>
}