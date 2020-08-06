package com.bigangrydinosaur.blimp.services

import org.springframework.stereotype.Service

interface CodeProvider {
    fun generate(): String
}

@Service
class CodeService: CodeProvider {

    override fun generate(): String {
        TODO("Not yet implemented")
    }

}
