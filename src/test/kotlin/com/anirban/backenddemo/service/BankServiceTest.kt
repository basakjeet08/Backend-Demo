package com.anirban.backenddemo.service

import com.anirban.backenddemo.datasource.BankDataSource
import com.anirban.backenddemo.model.BankData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class BankServiceTest {

    // This is the bank data source Mock implementation of the data source Interface
    private val bankDataSource: BankDataSource = mockk(relaxed = true)

    // This is the Object of the bank service Class
    private val bankService = BankService(bankDataSource)

    /**
     * This function checks if ---
     *
     *      1. The service function is calling the function inside the bank Data Source implementation
     */
    @Test
    fun `should call the function to retrieve data`() {

        // given
        // When the retrieveData Method is called the retrieveData function returns an empty List (For testing Purposes)
        every { bankDataSource.retrieveData() } returns emptyList()

        // when
        bankService.getBanks()

        // then
        // Checking if the retrieveData method is called or not
        verify(exactly = 1) {
            bankDataSource.retrieveData()
        }
    }

    /**
     * This function checks if ---
     *
     *      1. The Function inside the bank data source is getting called or not
     */
    @Test
    fun `should call the function to retrieve individual Bank Data`() {

        // Given
        every { bankDataSource.retrieveIndividualBank("21051880") } returns BankData(
            accountNumber = "21051880",
            trust = 86.8,
            transactionFee = 2000
        )

        // When
        bankService.getIndividualBank("21051880")

        // Then
        verify(exactly = 1) {
            bankDataSource.retrieveIndividualBank("21051880")
        }
    }

    /**
     * This Test checks if ---
     *
     *      1. The Service is calling the add bank data source function to add the Data
     */
    @Test
    fun `should call the function to add new Bank Data`() {

        // Given
        // bank variable for the test
        val bankData = BankData(
            accountNumber = "21051885",
            trust = 43.09,
            transactionFee = 2
        )

        every { bankDataSource.addBankData(bankData) } returns bankData

        // when
        bankService.addBank(bankData)

        // then
        verify(exactly = 1) {
            bankDataSource.addBankData(bankData)
        }
    }

    /**
     * This function checks if ---
     *
     *      1. The Function is calling the Data Source function
     */
    @Test
    fun `should call data source Function`() {

        // Given
        val bankData = BankData(
            accountNumber = "21051880",
            trust = 43.09,
            transactionFee = 2
        )

        every { bankDataSource.updateBankData(bankData) } returns bankData

        // when
        bankService.updateData(bankData)

        // Then
        verify(exactly = 1) {
            bankDataSource.updateBankData(bankData)
        }
    }

    /**
     * This function tests if ---
     *
     *      1. The data source function is getting called or not
     */
    @Test
    fun `should call the data source delete Function`() {

        val accountNumber = "21051880"

        // Given
        every { bankDataSource.deleteBankData(accountNumber) } returns Unit

        // When
        bankService.deleteBankData(accountNumber)

        // Then
        verify(exactly = 1) {
            bankDataSource.deleteBankData(accountNumber)
        }
    }
}