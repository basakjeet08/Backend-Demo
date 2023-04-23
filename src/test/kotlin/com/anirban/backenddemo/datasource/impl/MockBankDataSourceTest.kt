package com.anirban.backenddemo.datasource.impl

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


/**
 * This is the Bank Data Source JUnit Test Class which contains the test cases related to the specific Class
 */
class MocBankDataSourceTest {

    // This is the mock bank data source Object which is a testing Implementation of the Data Source Interface
    private val mockBankDataSource = MockBankDataSource()

    /*
     * This class checks if the list returned by the function is empty or not
     * (If empty then the test case fails otherwise true)
     */
    @Test
    fun `should provide a list of collection of banks`() {

        // when
        val banks = mockBankDataSource.retrieveData()

        // Then
        assertThat(banks).isNotEmpty
    }

    /*
    This class checks if all the data inside each List Bank Data is empty or not
    If empty then it returns test failed otherwise test succeeds
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