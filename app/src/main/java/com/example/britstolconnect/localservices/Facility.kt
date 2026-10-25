
package com.example.bristolconnect.localservices

data class Facility(
    val id: Int,
    val name: String,
    val category: String,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val openingHours: String,
    val description: String
)
