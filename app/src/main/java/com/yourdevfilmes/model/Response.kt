package com.yourdevfilmes.model

data class Response (
    val status: String,
    val copyright : String,
    var has_more: Boolean,
    var num_results: Int,
    val results: ArrayList<Result>
)