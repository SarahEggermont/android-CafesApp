package com.example.boardgamesapp.model

data class Cafe(
    var id: Int = 0,
    val poi: String = "",
    val nameNl: String = "",
    val nameEn: String = "",
    val nameFr: String = "",
    val nameDe: String = "",
    val nameEs: String = "",
    val descriptionNl: String = "",
    val descriptionEn: String = "",
    val descriptionFr: String = "",
    val descriptionDe: String = "",
    val descriptionEs: String = "",
    val url: String = "",
    val modified: String = "",
    val catnameNl: String = "",
    val address: String = "",
    val postal: String = "",
    val local: String = "",
    val icon: String = "",
    val type: String = "",
    val symbol: String = "",
    val identifier: Int = 0,
    val geoPoint: List<Double> = listOf(0.0, 0.0)
)
