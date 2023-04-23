package com.anirban.backenddemo.service

import com.anirban.backenddemo.datasource.BankDataSource
import com.anirban.backenddemo.model.BankData
import org.springframework.stereotype.Service


/**
 * This class is the Bank Service Use Case Class which contains all the business logics related to the Bank Data
 *
 * After the Repository Layer this Layer comes above it also known as the User Cases / Domain Layer / Services Layer
 */
@Service
class BankService(private val bankDataSource: BankDataSource) {

    // This function gets the Bank Data from the repository and returns it to the Person Asking for the Data
    fun getBanks(): List<BankData> {
        return bankDataSource.retrieveData()
    }
}