package com.github.wakingrufus.strats

@StratsDsl
open class Beastiary {
    private val monsters: MutableList<Monster> = mutableListOf()
    fun monster(name: String, builder: Monster.() -> Unit): Monster =
            Monster(name).apply(builder).also { monsters.add(it) }

    fun monster(monster: Monster) = monsters.add(monster)
    fun monsters(): List<Monster> = monsters
}

@StratsDsl
class Monster(val name: String) {
    var strategy: String? = null
    val data: MutableList<MonsterDatum> = mutableListOf()
    fun strategy(text: String) {
        strategy = text
    }

    fun scalarData(name: String, value: Number) {
        data.add(MonsterDatum.ScalarMonsterDatum(name, value))
    }

    fun rangeData(name: String, minValue: Number, maxValue: Number) {
        data.add(MonsterDatum.RangeMonsterDatum(name, minValue, maxValue))
    }

    fun itemTable(name: String, item: Item, chance: Number) {
        val existing = data.filterIsInstance<MonsterDatum.TabularItemMonsterDatum>().find { it.name == name }
        if (existing == null) {
            data.add(MonsterDatum.TabularItemMonsterDatum(name, mutableListOf(MonsterDatum.ScalarMonsterItemDatum(item, chance))))
        } else {
            existing.values.add(MonsterDatum.ScalarMonsterItemDatum(item, chance))
        }
    }
}

sealed class MonsterDatum {
    class ScalarMonsterDatum(val name: String, val value: Number) : MonsterDatum()
    class RangeMonsterDatum(val name: String, minValue: Number, maxValue: Number) : MonsterDatum()
    class ScalarMonsterItemDatum(val item: Item, val value: Number) : MonsterDatum()
    class TabularMonsterDatum(val name: String, val values: MutableList<ScalarMonsterDatum>) : MonsterDatum()
    class TabularItemMonsterDatum(val name: String, val values: MutableList<ScalarMonsterItemDatum>) : MonsterDatum()
}

//fun monster(name: String, builder: Monster.() -> Unit): Monster = Monster(name).apply(builder)