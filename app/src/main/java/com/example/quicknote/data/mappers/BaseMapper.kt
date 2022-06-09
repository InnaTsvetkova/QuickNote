package com.example.quicknote.data.mappers

abstract class BaseMapper<in IN, out OUT> {
    abstract fun transform(entity: IN): OUT

    fun transform(entities: List<IN>): List<OUT>{
        return entities.map {
            transform(it)
        }
    }
}