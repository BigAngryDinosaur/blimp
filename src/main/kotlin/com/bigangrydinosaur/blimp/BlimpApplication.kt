package com.bigangrydinosaur.blimp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlimpApplication

fun main(args: Array<String>) {
	runApplication<BlimpApplication>(*args)
}
