package com.bigangrydinosaur.blimp.repositories

import com.bigangrydinosaur.blimp.models.Url
import org.springframework.data.repository.CrudRepository

interface UrlRepository: CrudRepository<Url, Long> {
    fun findByCode(code: String): Url?
    fun save(url: Url): Url
}