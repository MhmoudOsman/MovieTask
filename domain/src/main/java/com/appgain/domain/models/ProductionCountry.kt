package com.appgain.domain.models

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
){
    override fun toString(): String {
        return "ProductionCountry(" +
                "iso_3166_1='$iso_3166_1'," +
                " name='$name')"
    }
}