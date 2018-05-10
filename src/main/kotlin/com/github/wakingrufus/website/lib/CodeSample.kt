package com.github.wakingrufus.website.lib

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec
/*
class CodeSample(val className: ClassName) {
    var builder = FileSpec.builder("", className.simpleName())
    fun type(name: String, block: Type.() -> Unit) {
        builder.addType(builder.addType(block.invoke(Type(name))))
    }

    fun build() {
        builder.build()
    }

}

class Type(className: String) {
    var builder = TypeSpec.classBuilder(className)
    fun primaryConstructor(c: FunSpec) {
        builder.primaryConstructor(c)
    }
}
*/