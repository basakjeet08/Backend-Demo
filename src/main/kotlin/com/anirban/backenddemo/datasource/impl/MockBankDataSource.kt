package com.anirban.backenddemo.datasource.impl

import com.anirban.backenddemo.datasource.BankDataSource
import com.anirban.backenddemo.model.BankData
import org.springframework.stereotype.Repository


/**
 * This is the Fake Bank Data Source Implementation for Testing the Functions and running the JUnit Test Cases
 *
 * This class can be referred as the repository class
 *
 * Moving Forward with the TDD Programming Approach and writing the test cases before writing the actual functions
 */
@Repository
class MockBankDataSource : BankDataSource {

    // This function returns the Bank Data Collected from the source
    override fun retrieveData(): List<BankData> {
        return FakeBankData.bankData
    }

    // This function returns the Bank Data having the accountNumber given Collected from the Source
    override fun retrieveIndividualBank(accountNumber: String): BankData {
        return FakeBankData.bankData.find {
            it.accountNumber == accountNumber
        } ?: throw NoSuchElementException("Account Number : $accountNumber Not Found")
    }
}