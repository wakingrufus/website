package com.github.wakingrufus.strats

import com.github.wakingrufus.strats.FFXIIItems.Elixer
import com.github.wakingrufus.strats.FFXIIItems.Ether
import com.github.wakingrufus.strats.FFXIIItems.Potion
import com.github.wakingrufus.strats.FFXIIItems.ThiefsCuffs


object FFXIIBeastiary : Beastiary() {
    val airCutterRemora = monster("Air Cutter Remora") {
        level(10)
        hp(2200)
        steal(Potion, .5)
        steal(Ether, .1, ThiefsCuffs)
        steal(Elixer, .1, ThiefsCuffs)
        strategy("""
            The first boss battle is designed to be extremely easy. 
The attacks from the other ally soldiers are enough to take down the boss without your help but you may as well join the fray to speed the battle up. 
Reks will normally only be hit by Area of Effect (or AoE) attacks, 
which are attacks that hit multiple party members. 
You can use Potions by pressing the X Button and selecting the Item command if you need to refill Reks’ HP.
Basch’s attacks deal a very high amount of damage and he will eventually end the fight by using one of his special moves once Air Cutter Remora’s HP drops to around 50%. 
The battle will end automatically at this point.
        """.trimIndent())
    }
    val imperialSwordsman = monster("Imperial Swordsman") {
        levelRange(35, 36)
    }
    val direRat = monster("Dire Rat") {

    }
}

fun Monster.hp(points: Int) {
    scalarData("HP", points)
}

fun Monster.level(level: Int) {
    scalarData("Level", level)
}

fun Monster.levelRange(minLevel: Int, maxLevel: Int) {
    rangeData("Level", minLevel, maxLevel)
}

fun Monster.drop(item: Item, chance: Double, modifierItem: Item) {
    itemTable("Drop", item, chance)
}

fun Monster.steal(item: Item, chance: Double, modifierItem: Item? = null) {
    itemTable("Steal", item, chance)
}
