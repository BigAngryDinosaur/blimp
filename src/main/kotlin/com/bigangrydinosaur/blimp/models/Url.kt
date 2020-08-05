package com.bigangrydinosaur.blimp.models

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Url(
    @Id @GeneratedValue var id: Long? = null,
    var code: String,
    var url: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var expirationDate: LocalDateTime? = null
)