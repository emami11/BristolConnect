package com.example.britstolconnect

import com.example.bristolconnect.localservices.FacilityRepository
import com.example.bristolconnect.localservices.filterFacilities
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FacilityFilterTest {

    private val facilities =
        FacilityRepository().getAllFacilities()

    @Test
    fun allCategoryReturnsSixFacilities() {

        val results = filterFacilities(
            facilities,
            "",
            "All"
        )

        assertEquals(6, results.size)
    }

    @Test
    fun librariesFilterReturnsThreeLibraries() {

        val results = filterFacilities(
            facilities,
            "",
            "Libraries"
        )

        assertEquals(3, results.size)

        assertTrue(
            results.all { it.category == "Library" }
        )
    }

    @Test
    fun recyclingFilterReturnsThreeCentres() {

        val results = filterFacilities(
            facilities,
            "",
            "Recycling"
        )

        assertEquals(3, results.size)

        assertTrue(
            results.all {
                it.category == "Recycling Centre"
            }
        )
    }

    @Test
    fun searchByFacilityNameWorks() {

        val results = filterFacilities(
            facilities,
            "Bedminster",
            "All"
        )

        assertEquals(1, results.size)

        assertEquals(
            "Bedminster Library",
            results.first().name
        )
    }

    @Test
    fun searchIsCaseInsensitive() {

        val results = filterFacilities(
            facilities,
            "FISHPONDS",
            "All"
        )

        assertEquals(1, results.size)

        assertEquals(
            "Fishponds Library",
            results.first().name
        )
    }

    @Test
    fun searchByAddressWorks() {

        val results = filterFacilities(
            facilities,
            "Deanery Road",
            "All"
        )

        assertEquals(1, results.size)

        assertEquals(
            "Bristol Central Library",
            results.first().name
        )
    }

    @Test
    fun searchAndCategoryWorkTogether() {

        val results = filterFacilities(
            facilities,
            "Bedminster",
            "Recycling"
        )

        assertTrue(results.isEmpty())
    }

    @Test
    fun unknownSearchReturnsEmptyList() {

        val results = filterFacilities(
            facilities,
            "Unknown Hospital",
            "All"
        )

        assertTrue(results.isEmpty())
    }

    @Test
    fun whitespaceInSearchIsIgnored() {

        val results = filterFacilities(
            facilities,
            "  Bedminster  ",
            "All"
        )

        assertEquals(1, results.size)
    }
}
