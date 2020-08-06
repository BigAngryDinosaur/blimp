package com.bigangrydinosaur.blimp.services

import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

@ExtendWith(MockitoExtension::class)
class CodeServiceTests {

    @Mock
    lateinit var uniqueNumberGenerator: UniqueNumberGenerator

    @InjectMocks
    lateinit var codeService: CodeService

    @Test
    fun `when generate a code should return a valid code`() {
        given(uniqueNumberGenerator.next()).willReturn(128)
        val code = codeService.generate()
        assertThat(code, equalTo("24"))
    }
}
