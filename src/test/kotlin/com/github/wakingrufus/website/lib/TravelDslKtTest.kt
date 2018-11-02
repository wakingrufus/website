package com.github.wakingrufus.website.lib


import assertk.assert
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class TravelDslKtTest {

    @Test
    fun `test NestedAreaLevel`() {
        assert(NestedAreaLevel(1).next()).isEqualTo(NestedAreaLevel(2))
        assert(NestedAreaLevel(2).next(), "2 goes to 3").isEqualTo(NestedAreaLevel(3))
    }
}