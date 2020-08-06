package com.bigangrydinosaur.blimp.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.Instant
import kotlin.math.pow

@Service
class UniqueNumberGenerator(@Value("\${app.node.id}")
                            private var nodeID: Long) {

    private companion object {
        const val NODE_BITS = 10
        const val SEQUENCE_BITS = 12
        val MAX_NODE_IDS = 2.0.pow(NODE_BITS).toLong()
        val MAX_SEQUENCE_BITS = 2.0.pow(SEQUENCE_BITS).toLong()
    }

    private var lastTimeStamp: Long = -1
    private var sequence = 0L

    init { require(nodeID in 0 until MAX_NODE_IDS) }

    fun next(): Long {
        var timestamp = Instant.now().toEpochMilli()

        if (timestamp == lastTimeStamp) {
            sequence += 1
            if (sequence == MAX_SEQUENCE_BITS) {
                timestamp = nextMilliSecond(timestamp)
            }
        } else {
            sequence = 0
        }

        lastTimeStamp = timestamp

        var num = timestamp shl (NODE_BITS + SEQUENCE_BITS)
        num = num or (nodeID shl SEQUENCE_BITS)
        num = num or sequence

        return num
    }

    fun nextMilliSecond(currentMilliSecond: Long): Long {
        var currentMilliSecond = currentMilliSecond
        while (currentMilliSecond == lastTimeStamp) {
            currentMilliSecond = Instant.now().toEpochMilli()
        }
        return currentMilliSecond
    }
}