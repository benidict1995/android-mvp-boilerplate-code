package com.benidict.domain.model

data class Article(
    val author: String?= null,
    val title:String?= null,
    val description:String?= null,
    val url:String?= null,
    val urlToImage:String?= null,
    val publishedAt: String?= null
)