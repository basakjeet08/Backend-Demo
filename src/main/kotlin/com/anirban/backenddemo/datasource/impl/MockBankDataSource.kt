package com.anirban.backenddemo.datasource.impl

import com.anirban.backenddemo.datasource.BankDataSource
import com.anirban.backenddemo.exceptions.BadRequestException
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

    // This function adds the new bank data to the database
    override fun addBankData(bankData: BankData): BankData {

        // Checking if the bank Data is already present in the Database if so then we return Bad Request as response
        if (FakeBankData.bankData.any {
                it.accountNumber == bankData.accountNumber
            }) {
            throw BadRequestException("Bank Details already Present")
        }

        // Adding the Data to the database
        FakeBankData.bankData.add(bankData)

        return bankData
    }

    // This function updates an existing Bank Data into the database
    override fun updateBankData(bankData: BankData): BankData {

        var isBankDataUpdated = false

        FakeBankData.bankData.forEachIndexed { index, data ->
            if (data.accountNumber == bankData.accountNumber) {
                FakeBankData.bankData[index] = bankData
                isBankDataUpdated = true
            }
        }

        if (!isBankDataUpdated)
            throw BadRequestException("Account Number : ${bankData.accountNumber} is not Present")

        return bankData
    }
}