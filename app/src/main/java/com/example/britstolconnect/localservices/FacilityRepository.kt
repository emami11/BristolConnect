
package com.example.bristolconnect.localservices

class FacilityRepository {

    private val facilities = listOf(
        Facility(
            id = 1,
            name = "Bristol Central Library",
            category = "Library",
            address = "College Green, Bristol",
            latitude = 51.4526,
            longitude = -2.6003,
            openingHours = "Check official website",
            description = "Public library offering books and study facilities."
        ),
        Facility(
            id = 2,
            name = "Hartcliffe Way Recycling Centre",
            category = "Recycling Centre",
            address = "Hartcliffe Way, Bristol",
            latitude = 51.4200,
            longitude = -2.6000,
            openingHours = "Check official website",
            description = "Recycling centre for household waste."
        )
    )

    fun getAllFacilities(): List<Facility> {
        return facilities
    }
}
