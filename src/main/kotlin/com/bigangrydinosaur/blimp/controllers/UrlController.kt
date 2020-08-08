package com.bigangrydinosaur.blimp.controllers

import com.bigangrydinosaur.blimp.models.Url
import com.bigangrydinosaur.blimp.repositories.UrlRepository
import com.bigangrydinosaur.blimp.services.CodeProvider
import com.bigangrydinosaur.blimp.services.CodeService
import org.springframework.web.bind.annotation.*

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

}