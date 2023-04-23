package com.anirban.backenddemo.controller

import com.anirban.backenddemo.model.BankData
import com.fasterxml.jackson.databind.ObjectMapper
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
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

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

    /**
     * This class checks the post Bank Request Test Cases
     */
    @Nested
    @DisplayName("Post Bank Request")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostBankData {

        // Base URL of the Individual Bank Endpoint
        private val baseUrl = "/api/banks"


        /**
         * This test checks if ---
         *
         *      1. The Controller is taking the endpoint correctly
         *      2. The JSON Format Data is correct or not
         */
        @Test
        fun `should add a new Bank Account to the Database`() {

            // given
            val bankData = BankData(
                accountNumber = "21051885",
                trust = 43.09,
                transactionFee = 2
            )

            // when
            val performAction = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bankData)
            }

            // then
            performAction
                .andDo {
                    print()
                }
                .andExpect {
                    status {
                        isCreated()
                    }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                    }
                    jsonPath("$.accountNumber") {
                        value("21051885")
                    }
                    jsonPath("$.trust") {
                        value(43.09)
                    }
                    jsonPath("$.transactionFee") {
                        value(2)
                    }
                }
        }

        /**
         * This function tests if ---
         *
         *      1. The Controller is returning a BAD REQUEST when someone tries to add a Bank Data which is already present
         */
        @Test
        fun `should return a BAD REQUEST if the bank Data is already present in the database`() {

            // given
            val bankData = BankData(
                accountNumber = "21051880",
                trust = 86.8,
                transactionFee = 2000
            )

            // when
            val performAction = mockMvc.post(baseUrl) {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bankData)
            }

            // then
            performAction
                .andDo {
                    print()
                }
                .andExpect {
                    status {
                        isBadRequest()
                    }
                }
        }
    }
}