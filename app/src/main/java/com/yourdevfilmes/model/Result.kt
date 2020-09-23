package com.yourdevfilmes.model

import java.util.*

data class Result (

    val display_title: String,
    val mpaa_rating: String,
    val critics_pick: Double,
    val byline: String,
    val headline: String,
    val summary_short: String,
    val publication_date: Date,   // Date
    val opening_date: Date,    // Date
    val date_updated: Date,    // Date

    val link : Link,
    val multimedia: Multimedia?
)