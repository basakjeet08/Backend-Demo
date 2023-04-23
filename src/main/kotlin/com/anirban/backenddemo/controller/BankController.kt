package com.anirban.backenddemo.controller

import com.anirban.backenddemo.model.BankData
import com.anirban.backenddemo.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * This is the controller class which controls all the incoming API requests and maps them to the place they should
 * go to get the required behaviour
 */
@RestController
@RequestMapping("/api")
class BankController(private val bankService: BankService) {

    // This function handles all the NO Such Element Found Exception and returns HTTP NOT FOUND response with error message
    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElementFoundExceptionHandler(e: NoSuchElementException): ResponseEntity<String> {
        println("Exception Found ")
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }


    /**
     * This function fetches all the Bank Data and returns it to the Client Asking It
     *
     * To fetch all the Bank Data use the endpoint ---
     *
     *      /api/banks
     */
    @GetMapping("/banks")
    fun getBanks(): List<BankData> {
        println("Fetching all Banks ")
        return bankService.getBanks()
    }

    /**
     * This function fetches the Bank Data which has the same account Number and returns it to the Client
     *
     * To Fetch the Bank Data of a particular bank user this endpoint ---
     *
     *      api/banks/<bankAccountNumber>
     */
    @GetMapping("/banks/{accountNumber}")
    @ResponseBody
    fun getIndividualBank(@PathVariable accountNumber: String): BankData? {
        println("Fetching individual Banks according to the accountNumber")
        return bankService.getIndividualBank(accountNumber = accountNumber)
    }
}