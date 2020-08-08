package com.bigangrydinosaur.blimp.services

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.extension.ExtendWith
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

@ExtendWith(MockKExtension::class)
class CodeServiceTests {

    @MockkBean
    lateinit var uniqueNumberGenerator: UniqueNumberGenerator

    @InjectMockKs
    lateinit var codeService: CodeService

    @Test
    fun `when generate a code should return a valid code`() {
        every { uniqueNumberGenerator.next()  } returns 128
        val code = codeService.generate()
        assertThat(code, equalTo("24"))
    }
}
