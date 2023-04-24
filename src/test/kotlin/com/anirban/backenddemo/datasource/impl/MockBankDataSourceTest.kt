package com.anirban.backenddemo.datasource.impl

import com.anirban.backenddemo.exceptions.BadRequestException
import com.anirban.backenddemo.model.BankData
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


/**
 * This is the Bank Data Source JUnit Test Class which contains the test cases related to the specific Class
 */
class MocBankDataSourceTest {

    // This is the mock bank data source Object which is a testing Implementation of the Data Source Interface
    private val mockBankDataSource = MockBankDataSource()


    /**
     * This test Class checks all the cases with All Banks
     */
    @Nested
    @DisplayName("Data Source All Bank Cases")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DataSourceAllBank {

        /**
         * This class checks if ---
         *
         *      1. The list returned by the function is empty or not (If empty then the test case fails otherwise true)
         */
        @Test
        fun `should provide a list of collection of banks`() {

            // when
            val banks = mockBankDataSource.retrieveData()

            // Then
            assertThat(banks).isNotEmpty
        }

        /**
         * This class checks if ---
         *
         *      1. All the data inside each List Bank Data is empty or not ,If empty then it returns test failed otherwise test succeeds
         */
        @Test
        fun `should not return a empty Mock Bank Data`() {

            // when
            val banks = mockBankDataSource.retrieveData()

            // Then
            assertThat(banks).allMatch {
                it.accountNumber.isNotBlank()
            }
            assertThat(banks).allMatch {
                it.transactionFee != 0
            }
            assertThat(banks).allMatch {
                it.trust != 0.0
            }
        }


    }

    /**
     * This Test cases checks all the Individual Bank data sources cases
     */
    @Nested
    @DisplayName("Data Source Single Bank Cases")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DataSourceIndividualBank {

        /**
         * This function checks if ---
         *
         *      1. The Data is returned from the function
         *      2. If the data fields are valid or not
         */
        @Test
        fun `should return a single bank Data which is valid`() {

            // When
            val bank = mockBankDataSource.retrieveIndividualBank("21051880")

            // Then
            // Checking if the data is Null
            assertThat(bank).isNotNull

            // Checking if the data returned is correct and is not empty
            assertThat(bank).matches {
                it.accountNumber == "21051880"
            }
            assertThat(bank).matches {
                it.trust != 0.0
                it.transactionFee != 0
                it.accountNumber.isNotEmpty()
            }
        }
    }

    /**
     * This class checks the adding of new bank data to the database
     */
    @Nested
    @DisplayName("Data Source Add Bank")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DataSourceAddBank {

        /**
         * This function checks if ---
         *
         *      1. The data is being added
         *      2. The function is returning the same Bank Data given to it
         */
        @Test
        fun `should add the new bank Data to the database and returns the Bank Data`() {

            // given
            val bankData = BankData(
                accountNumber = "hjh",
                trust = 43.09,
                transactionFee = 2
            )

            // when
            val bank = mockBankDataSource.addBankData(bankData)

            // then
            assertThat(bank == bankData)
        }
    }

    /**
     * This function checks if ---
     *
     *      1. The function is throwing a BadRequestException when we are trying to enter a bank details which is already present
     */
    @Test
    fun `should throw an error when adding already present Bank Data`() {

        // Given
        val bankData = BankData(
            accountNumber = "21051880",
            trust = 86.8,
            transactionFee = 2000
        )

        var isExceptionFound = false

        // When
        try {
            mockBankDataSource.addBankData(bankData)
        } catch (e: BadRequestException) {
            isExceptionFound = true
        }

        // Then
        assertThat(isExceptionFound)
    }

    /**
     * This class checks all the Data Source Test Cases for updating a Data in the Database
     */
    @Nested
    @DisplayName("Update/Patch Bank Data")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class UpdateBankData {

        /**
         * This function checks if ---
         *
         *      1. The Function is returning the updated bank Data
         */
        @Test
        fun `should Return the updated Bank Data`() {

            // Given
            val bankData = BankData(
                accountNumber = "21051880",
                trust = 92.34,
                transactionFee = 123
            )

            // When
            val bank = mockBankDataSource.updateBankData(bankData)

            assertThat(bank == bankData)
        }

        /**
         * This function is testing if ---
         *
         *      1. The function is throwing BadRequestException when the data given is not even present in the Database
         */
        @Test
        fun `should throw BAD REQUEST EXCEPTION when the Bank Data is not Present in the database`() {

            // Given
            val bankData = BankData(
                accountNumber = "saffron",
                trust = 92.34,
                transactionFee = 123
            )

            var isBadRequest = false

            // When
            try {
                mockBankDataSource.updateBankData(bankData)
            } catch (e: BadRequestException) {
                isBadRequest = true
            }

            assertThat(isBadRequest)
        }
    }

    /**
     * This class tests all the Cases that may arrive while deleting a Bank Data
     */
    @Nested
    @DisplayName("Delete Bank Data Source")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteDataSource {

        /**
         * This class tests if ---
         *
         *      1. If the Function is throwing a BAD REQUEST when the account Number doesn't
         *      exist
         */
        @Test
        fun `should throw a NULL POINTER EXCEPTION when the data is not there in the database`() {

            // Given
            val accountNumber = "does not exists"
            var isBadRequest = false

            // When
            try {
                mockBankDataSource.deleteBankData(accountNumber)
            } catch (e: NoSuchElementException) {
                isBadRequest = true
            }

            assertThat(isBadRequest)
        }

        /**
         * This function is testing if ---
         *
         *      1. The function is deleting the data from the Database
         */
        @Test
        fun `should delete an already present bank Data from the Database`() {

            // Given
            val accountNumber = "21051880"
            var isNotBadRequest = true

            // When
            try {
                mockBankDataSource.deleteBankData(accountNumber)
            } catch (e: BadRequestException) {
                isNotBadRequest = false
            }

            // Then
            assertThat(isNotBadRequest)
        }
    }
}