package com.appgain.domain.models

data class MovieDetailsResponse(
    var adult: Boolean,
    var backdrop_path: String,
    var belongs_to_collection: Any,
    var budget: Int,
    var genres: List<Genre>,
    var homepage: String,
    var id: Int,
    var imdb_id: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var production_companies: List<ProductionCompany>,
    var production_countries: List<ProductionCountry>,
    var release_date: String,
    var revenue: Int,
    var runtime: Int,
    var spoken_languages: List<SpokenLanguage>,
    var status: String,
    var tagline: String,
    var title: String,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
){

    override fun toString(): String {
        return "MovieDetailsResponse(" +
                "adult=$adult," +
                " backdrop_path='$backdrop_path'," +
                " belongs_to_collection=$belongs_to_collection," +
                " budget=$budget," +
                " genres=$genres," +
                " homepage='$homepage'," +
                " id=$id," +
                " imdb_id='$imdb_id'," +
                " original_language='$original_language'," +
                " original_title='$original_title'," +
                " overview='$overview'," +
                " popularity=$popularity," +
                " poster_path='$poster_path'," +
                " production_companies=$production_companies," +
                " production_countries=$production_countries," +
                " release_date='$release_date'," +
                " revenue=$revenue," +
                " runtime=$runtime," +
                " spoken_languages=$spoken_languages," +
                " status='$status'," +
                " tagline='$tagline'," +
                " title='$title'," +
                " video=$video," +
                " vote_average=$vote_average," +
                " vote_count=$vote_count)"
    }
}