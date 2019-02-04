package com.github.wakingrufus.website.lib.cooking

import java.time.Duration
import java.time.temporal.ChronoUnit

@RecipeDsl
class STEP(val description: String) {
    private var duration: Duration? = null
    fun duration(amount: Long, unit: ChronoUnit) {
        duration = Duration.of(amount, unit)
    }
}