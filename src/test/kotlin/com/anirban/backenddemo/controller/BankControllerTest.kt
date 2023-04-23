package com.anirban.backenddemo.controller

import org.junit.jupiter.api.Test
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

    /*
    This test case checks if the Controller is getting called and if the controller is sending a data of type JSON
     */
    @Test
    fun `should return all banks`() {

        // when / then
        mockMvc.get("/api/banks")
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