package com.github.wakingrufus.strats

@StratsDsl
data class Game(val name: String) {
    private val variants: MutableList<GameVariant> = mutableListOf()
    fun variant(name: String): GameVariant = GameVariant(name).also { variants.add(it) }

    val beastiary: Beastiary = Beastiary()
    fun beastiary(builder: Beastiary.() -> Unit){
        beastiary(builder)
    }

    fun beastiary(b: Beastiary){
        b.monsters().forEach { beastiary.monster(it) }
    }

    private val walkthrough : Walkthrough = Walkthrough()
    fun walkthrough(builder: Walkthrough.() -> Unit){
        walkthrough.apply(builder)
    }
}

fun game(builder: Game.() -> Unit) {

}


fun Game.toText(): String{
    val sb = StringBuilder()
    sb.appendln(name)

    sb.appendln("Beastiary")
    beastiary.monsters().forEach { sb.appendln(it.toString()) }
    return sb.toString()
}
