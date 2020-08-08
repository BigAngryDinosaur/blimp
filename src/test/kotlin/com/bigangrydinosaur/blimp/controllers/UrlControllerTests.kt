package com.bigangrydinosaur.blimp.controllers

import com.bigangrydinosaur.blimp.models.Url
import com.bigangrydinosaur.blimp.repositories.UrlRepository
import com.bigangrydinosaur.blimp.services.CodeService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(UrlController::class)
class UrlControllerTests(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var codeService: CodeService

    @MockkBean
    lateinit var urlRepository: UrlRepository

    @Test
    fun `create shorturl`() {
        val code = "qwerty"
        val fullUrl = "http://www.reddit.com"
        val url = Url(code = code, url = fullUrl)
        every { urlRepository.save(any()) } returns url
        every { codeService.generate() } returns code

        mockMvc
                .perform(post("/api/url/")
                        .content(fullUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.code").value(code))
                .andExpect(jsonPath("\$.url").value(fullUrl))
    }

}