package com.github.wakingrufus.strats

@StratsDsl
class Walkthrough {
    val sections: MutableList<Section> = mutableListOf()
    fun section(name: String, builder: Section.() -> Unit): Section = Section(name)
            .apply(builder)
            .also { sections.add(it) }
}

@StratsDsl
class Section(val name: String){
    val monsters : MutableList<Monster> = mutableListOf()
    fun areaMonsters(vararg monster : Monster){
        monsters.addAll(monster)
    }
    fun step(description: String){

    }
    fun battle(monster: Monster){

    }
    fun shop(){

    }

}