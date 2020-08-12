package com.bigangrydinosaur.blimp.controllers

import com.bigangrydinosaur.blimp.models.Url
import com.bigangrydinosaur.blimp.repositories.UrlRepository
import com.bigangrydinosaur.blimp.services.CodeProvider
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/url")
class UrlController(val codeService: CodeProvider,
                    val urlRepository: UrlRepository) {

    @PostMapping("/")
    fun newUrl(@RequestBody fullUrl: String): Url {
        val shortCode = codeService.generate()
        val newUrl = Url(code = shortCode, url = fullUrl)
        return urlRepository.save(newUrl)
    }

    @GetMapping("/{code}")
    fun getUrl(@PathVariable("code") code: String): Url = urlRepository.findByCode(code)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

}