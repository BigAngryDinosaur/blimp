package com.bigangrydinosaur.blimp.repositories

import com.bigangrydinosaur.blimp.models.Url
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UrlRepositoryTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val urlRepository: UrlRepository) {

    @Test
    fun `when FindByCode then return Url`() {
        val code = "qwerty"
        val url = Url(code = code, url = "https://www.reddit.com")
        entityManager.persistAndFlush(url)
        urlRepository.findByCode(code) `should be equal to` url
    }
}
