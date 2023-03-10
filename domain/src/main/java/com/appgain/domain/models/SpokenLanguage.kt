package com.appgain.domain.models

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String

) {
    override fun toString(): String {
        return "SpokenLanguage(" +
                "english_name='$english_name'," +
                " iso_639_1='$iso_639_1'," +
                " name='$name')"
    }
}