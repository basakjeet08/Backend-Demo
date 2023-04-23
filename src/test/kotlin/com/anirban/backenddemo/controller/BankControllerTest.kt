package com.anirban.backenddemo.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest {

    // This is a mock MVC which helps us to send http requests to the controller to test the controllers
    @Autowired
    lateinit var mockMvc: MockMvc


    /**
     * This inner class handles the test cases of all the instances when all the Banks data are asked
     */
    @Nested
    @DisplayName("Getting All Banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetAllBanks {

        // Base URL of the Individual Bank Endpoint
        private val baseUrl = "/api/banks"

        /**
         * This function checks if ---
         *
         *      1. The data of all Banks are returned
         *      2. If the returned data is in JSON Format
         */
        @Test
        fun `should return all Bank Data in JSON FORMAT`() {

            // when / then
            mockMvc.get(baseUrl)
                .andDo {
                    print()
                }
                .andExpect {
                    status {
                        isOk()
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$[0].accountNumber") {
                            value("21051880")
                        }
                    }
                }
        }
    }


    /**
     * This inner class handles the test cases of all the instances when individual Bank gets called
     */
    @Nested
    @DisplayName("Getting Individual Banks")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetIndividualBanks {

        // Base URL of the Individual Bank Endpoint
        private val baseUrl = "/api/banks"

        /**
         * This function checks if ----
         *
         *      1. the data of an individual Banks according to the Account Number are returned
         *      2. If the returned data is in JSON Format
         */
        @Test
        fun `should return a single bank which has the given Account Number in JSON FORMAT`() {

            // Account Number to be passed in the Path Variable
            val accountNumber = "21051880"

            // When / then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo {
                    print()
                }
                .andExpect {
                    status {
                        isOk()
                        content {
                            contentType(MediaType.APPLICATION_JSON)
                        }
                        jsonPath("$.accountNumber") {
                            value(accountNumber)
                        }
                    }
                }
        }

        /**
         * This function checks if ---
         *
         *      1. The data of individual Banks according to the account Number are returned
         *      2. If the returned data is in JSON Format
         */
        @Test
        fun `should give an Exception when the Bank of that account Number does not Exists`() {

            // Account Number to be passed in Path Variable
            val accountNumber = "does_not_exists"

            // When / then
            mockMvc.get("$baseUrl/$accountNumber")
                .andDo {
                    print()
                }
                .andExpect {
                    status {
                        isNotFound()
                    }
                }
        }
    }
}