package com.Heba.alquran.Modules

import com.google.gson.annotations.SerializedName



data class AppResponse(
        @SerializedName("code") val code: String?,
        @SerializedName("status") val status: String?,
        @SerializedName("data") val data: DataResponse?
)

data class SajdaResponse(
        @SerializedName("id") val id: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("value") val value: String?
)

data class AyahResponse(
        @SerializedName("number") val number: String?,
        @SerializedName("audio") val audio: String?,
        @SerializedName("audioSecondary") val audioSecondary: List<String>?,
        @SerializedName("text") val text: String?,
        @SerializedName("numberInSurah") val numberInSurah: String?,
        @SerializedName("juz") val juz: String?,
        @SerializedName("manzil") val manzil: String?,
        @SerializedName("page") val page: String?,
        @SerializedName("ruku") val ruku: String?,
        @SerializedName("hizbQuarter") val hizbQuarter: String?,
        @SerializedName("sajda") val sajda: Boolean? = false,
        @SerializedName("sajda") val sajdaResponse: SajdaResponse? = null
)

data class SurahsResponse(
        @SerializedName("number") val number: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("englishName") val englishName: String?,
        @SerializedName("englishNameTranslation") val englishNameTranslation: String?,
        @SerializedName("revelationType") val revelationType: String?,
        @SerializedName("ayahs") val ayahs: List<AyahResponse>?
)

data class DataResponse(
        @SerializedName("surahs") val surahs: List<SurahsResponse>?
)