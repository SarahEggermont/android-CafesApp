package com.example.boardgamesapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boardgamesapp.model.Cafe

@Entity(tableName = "cafes")
data class DbCafe(
    @PrimaryKey
    val objectid: Int,
    val poi: String = "",
    val name_nl: String = "",
    val name_en: String = "",
    val name_fr: String = "",
    val name_de: String = "",
    val name_es: String = "",
    val description_nl: String = "",
    val description_en: String = "",
    val description_fr: String = "",
    val description_de: String = "",
    val description_es: String = "",
    val url: String = "",
    val modified: String = "",
    val catname_nl: String = "",
    val address: String = "",
    val postal: String = "",
    val local: String = "",
    val icon: String = "",
    val type: String = "",
    val symbol: String = "",
    val identifier: Int = 0,
    val geo_point_x: Double = 0.0,
    val geo_point_y: Double = 0.0
)

fun DbCafe.asDomainCafe(): Cafe {
    return Cafe(
        this.objectid,
        this.poi,
        this.name_nl,
        this.name_en,
        this.name_fr,
        this.name_de,
        this.name_es,
        this.description_nl,
        this.description_en,
        this.description_fr,
        this.description_de,
        this.description_es,
        this.url,
        this.modified,
        this.catname_nl,
        this.address,
        this.postal,
        this.local,
        this.icon,
        this.type,
        this.symbol,
        this.identifier,
        listOf(this.geo_point_x, this.geo_point_y)
    )
}

fun Cafe.asDbCafe(): DbCafe {
    return DbCafe(
        objectid = this.id,
        poi = this.poi,
        name_nl = this.nameNl,
        name_en = this.nameEn,
        name_fr = this.nameFr,
        name_de = this.nameDe,
        name_es = this.nameEs,
        description_nl = this.descriptionNl,
        description_en = this.descriptionEn,
        description_fr = this.descriptionFr,
        description_de = this.descriptionDe,
        description_es = this.descriptionEs,
        url = this.url,
        modified = this.modified,
        catname_nl = this.catnameNl,
        address = this.address,
        postal = this.postal,
        local = this.local,
        icon = this.icon,
        type = this.type,
        symbol = this.symbol,
        identifier = this.identifier,
        geo_point_x = this.geoPoint[0],
        geo_point_y = this.geoPoint[1]
    )
}

fun List<DbCafe>.asDomainCafes(): List<Cafe> {
    val cafeList = this.map {
        Cafe(
            it.objectid,
            it.poi,
            it.name_nl,
            it.name_en,
            it.name_fr,
            it.name_de,
            it.name_es,
            it.description_nl,
            it.description_en,
            it.description_fr,
            it.description_de,
            it.description_es,
            it.url,
            it.modified,
            it.catname_nl,
            it.address,
            it.postal,
            it.local,
            it.icon,
            it.type,
            it.symbol,
            it.identifier,
            listOf(it.geo_point_x, it.geo_point_y)
        )
    }
    return cafeList
}
