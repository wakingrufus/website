package com.github.wakingrufus.strats

class Item(val name: String)

@StratsDsl
open class ItemDb {
    private val items: MutableList<Item> = mutableListOf()
    fun item(name: String): Item {
        return Item(name).also { items.add(it) }
    }
}