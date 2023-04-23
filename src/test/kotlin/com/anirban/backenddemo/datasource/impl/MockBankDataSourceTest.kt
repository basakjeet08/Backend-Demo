package com.anirban.backenddemo.datasource.impl

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
}