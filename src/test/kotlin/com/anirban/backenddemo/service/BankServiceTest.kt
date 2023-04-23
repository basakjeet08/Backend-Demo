package com.anirban.backenddemo.service

import com.anirban.backenddemo.datasource.BankDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class BankServiceTest {

    // This is the bank data source Mock implementation of the data source Interface
    private val bankDataSource: BankDataSource = mockk(relaxed = true)

    // This is the Object of the bank service Class
    private val bankService = BankService(bankDataSource)

    /*
    This function checks if the service function is calling the function inside the bank Data Source implementation
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
}