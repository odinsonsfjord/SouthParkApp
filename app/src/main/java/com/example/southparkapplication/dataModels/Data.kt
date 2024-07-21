package com.example.southparkapplication.dataModels

data class Data(
    val age: Int?,
    val created_at: String?,
    val episodes: List<String>?,
    val family: String?,
    val grade: String?,
    val hair_color: String,
    val id: Int?,
    val name: String?,
    val occupation: String?,
    val relatives: List<Relative>?,
    val religion: String?,
    val sex: String?,
    val updated_at: String?,
    val url: String?,
    val voiced_by: Any?
)