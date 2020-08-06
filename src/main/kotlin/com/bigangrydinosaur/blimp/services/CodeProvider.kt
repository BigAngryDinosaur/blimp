package com.bigangrydinosaur.blimp.services

import org.springframework.stereotype.Service

interface CodeProvider {
    fun generate(): String
}

@Service
class CodeService(val uniqueNumberGenerator: UniqueNumberGenerator): CodeProvider {

    val radix = 62

    override fun generate(): String {
        val seed = uniqueNumberGenerator.next()
        return encode(seed)
    }

    fun encode(num: Long): String {
        val chars = (0..9).map { "$it" }  + ('A'..'Z') + ('a'..'z')
        var result = ""
        var num = num

        while (num > 0) {
            val idx = num.rem(radix).toInt()
            val char = chars[idx]
            result = "$char$result"
            num /= radix
        }

        return result
    }

    fun decode(code: String): Int {
        var result = 0
        code.forEach { digit ->
            result *= radix
            result += when (digit) {
                in 'a'..'z' -> digit - 'a' + 10
                in 'A'..'Z' -> digit - 'A' + 36
                else        -> digit - '0'
            }
        }
        return result
    }
}

