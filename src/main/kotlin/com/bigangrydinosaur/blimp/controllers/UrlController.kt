package com.bigangrydinosaur.blimp.controllers

import com.bigangrydinosaur.blimp.models.Url
import com.bigangrydinosaur.blimp.repositories.UrlRepository
import com.bigangrydinosaur.blimp.services.CodeProvider
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.net.URL
import javax.servlet.http.HttpServletResponse

data class UrlRequest(val url: String)

@RestController
class UrlController(val codeService: CodeProvider,
                    val urlRepository: UrlRepository) {

    @PostMapping("/api/url")
    fun newUrl(@RequestBody urlRequest: UrlRequest): Url {
        val shortCode = codeService.generate()
        val newUrl = Url(code = shortCode, url = urlRequest.url)
        return urlRepository.save(newUrl)
    }

    @GetMapping("/u/{code}")
    fun getUrl(response: HttpServletResponse, @PathVariable("code") code: String) {
        val url = urlRepository.findByCode(code) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        var redirectUrl = url.url
        if (!redirectUrl.startsWith("http")) {
            redirectUrl = "http://${redirectUrl}"
        }
        response.sendRedirect(redirectUrl)
    }
}