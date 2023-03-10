package com.appgain.domain.models

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
){
    override fun toString(): String {
        return "ProductionCompany(" +
                "id=$id," +
                " logo_path='$logo_path'," +
                " name='$name'," +
                " origin_country='$origin_country')"
    }
}