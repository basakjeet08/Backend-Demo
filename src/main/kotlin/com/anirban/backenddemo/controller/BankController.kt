package com.anirban.backenddemo.controller

import com.anirban.backenddemo.model.BankData
import com.anirban.backenddemo.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * This is the controller class which controls all the incoming API requests and maps them to the place they should
 * go to get the required behaviour
 */
@RestController
@RequestMapping("/api")
class BankController(private val bankService: BankService) {

    // This is the function which executes when there is a get request at the endpoint "/api/banks"
    @GetMapping("/banks")
    fun getBanks(): List<BankData> {
        return bankService.getBanks()
    }

}