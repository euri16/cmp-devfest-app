package com.euryperez.devfest.app.data.talks

import kotlinx.serialization.Serializable

@Serializable
data class Talk(
    val id: String,
    val title: String,
    val description: String,
    val speaker: Speaker,
)

@Serializable
data class Speaker(
    val name: String,
    val headline: String,
    val bio: String,
    val imageUrl: String,
)
