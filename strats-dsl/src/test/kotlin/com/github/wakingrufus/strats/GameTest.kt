package com.github.wakingrufus.strats

import com.github.wakingrufus.strats.FFXIIBeastiary.airCutterRemora
import com.github.wakingrufus.strats.FFXIIBeastiary.direRat
import com.github.wakingrufus.strats.FFXIIBeastiary.imperialSwordsman
import org.junit.jupiter.api.Test

internal class GameTest {
    @Test
    fun test() {
        val game = game {
            val ps2 = variant("Original (PS2)")
            val switch = variant("Zodiac Age (Switch)")
            beastiary(FFXIIBeastiary)
            walkthrough {
                section("Nalbina Fortress") {
                    areaMonsters(imperialSwordsman)
                    step("Inner Ward")
                    battle(airCutterRemora)
                }
                section("The Royal City of Rabanastre") {
                    areaMonsters(direRat)
                }
            }
        }
    }
}

