
package com.example.bristolconnect

import com.example.bristolconnect.localservices.FacilityRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FacilityRepositoryTest {

    private val repository = FacilityRepository()

    @Test
    fun repositoryContainsSixFacilities() {
        val facilities = repository.getAllFacilities()

        assertEquals(6, facilities.size)
    }

    @Test
    fun everyFacilityHasUniqueId() {
        val facilities = repository.getAllFacilities()

        val ids = facilities.map { it.id }

        assertEquals(
            ids.size,
            ids.distinct().size
        )
    }

    @Test
    fun everyFacilityHasNameAndAddress() {
        val facilities = repository.getAllFacilities()

        facilities.forEach { facility ->
            assertTrue(facility.name.isNotBlank())
            assertTrue(facility.address.isNotBlank())
        }
    }

    @Test
    fun repositoryContainsThreeLibraries() {
        val facilities = repository.getAllFacilities()

        val libraries = facilities.filter {
            it.category == "Library"
        }

        assertEquals(3, libraries.size)
    }

    @Test
    fun repositoryContainsThreeRecyclingCentres() {
        val facilities = repository.getAllFacilities()

        val recyclingCentres = facilities.filter {
            it.category == "Recycling Centre"
        }

        assertEquals(3, recyclingCentres.size)
    }

    @Test
    fun recyclingCentresRequireBooking() {
        val facilities = repository.getAllFacilities()

        val recyclingCentres = facilities.filter {
            it.category == "Recycling Centre"
        }

        recyclingCentres.forEach { facility ->
            assertTrue(
                facility.openingHours.contains(
                    "Booking required",
                    ignoreCase = true
                )
            )
        }
    }
}
