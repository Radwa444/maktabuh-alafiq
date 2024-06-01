package com.example.maktabuhalafiq.data.models
data class BooksDownload(
    val id: Int,
    val title: String,
    val author: String,
    val downloadUrl: String,
    val coverImageUrl: String,
    val fileSize: Int
) {
    constructor() : this(1, "", "", "", "", 6)
}
