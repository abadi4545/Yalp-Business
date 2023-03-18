package com.arkam.yelp.model

data class ReservationClass(
    val email: String,
    val date: String,
    val time: String,
    val name: String,
)

data class ReservationsClass(
    val reservations: String
)