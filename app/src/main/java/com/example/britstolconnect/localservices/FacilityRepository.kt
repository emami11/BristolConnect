
package com.example.bristolconnect.localservices

class FacilityRepository {

    // List of local facilities in Bristol
    private val facilities = listOf(

        // 1. Bristol Central Library
        Facility(
            id = 1,
            name = "Bristol Central Library",
            category = "Library",
            address = "Deanery Road, Bristol, BS1 5TL",
            latitude = 0.0,
            longitude = 0.0,
            openingHours = """
                Monday: 9:30am - 5pm
                Tuesday: 9:30am - 7pm
                Wednesday: 9:30am - 5pm
                Thursday: 9:30am - 7pm
                Friday: 9:30am - 5pm
                Saturday: 10am - 5pm
                Sunday: 1pm - 5pm
            """.trimIndent(),
            description = "Central public library offering books, computers, Wi-Fi, study spaces, reference collections and printing services."
        ),

        // 2. Hartcliffe Way Recycling Centre
        Facility(
            id = 2,
            name = "Hartcliffe Way Reuse and Recycling Centre",
            category = "Recycling Centre",
            address = "83 Hartcliffe Way, Bristol, BS3 5RN",
            latitude = 0.0,
            longitude = 0.0,
            openingHours = """
                Monday: Closed
                Tuesday: Closed
                Wednesday: 9am - 5pm
                Thursday: 9am - 5pm
                Friday: 9am - 5pm
                Saturday: 8am - 4pm
                Sunday: 8am - 4pm
                Booking required
            """.trimIndent(),
            description = "Household reuse and recycling centre for Bristol residents. Visitors must book a visit and bring photo ID and proof of Bristol residence."
        ),

        // 3. Bedminster Library
        Facility(
            id = 3,
            name = "Bedminster Library",
            category = "Library",
            address = "4 Bedminster Parade, Bristol, BS3 4AQ",
            latitude = 0.0,
            longitude = 0.0,
            openingHours = """
                Monday: 10am - 7pm
                Tuesday: Closed
                Wednesday: 10am - 7pm
                Thursday: 10am - 5pm
                Friday: 10am - 5pm
                Saturday: 10am - 5pm
                Sunday: Closed
            """.trimIndent(),
            description = "Public library offering books, computers, Wi-Fi, scanning, printing and community activities."
        ),

        // 4. Fishponds Library
        Facility(
            id = 4,
            name = "Fishponds Library",
            category = "Library",
            address = "Hockeys Lane, Fishponds, Bristol, BS16 3HL",
            latitude = 0.0,
            longitude = 0.0,
            openingHours = """
                Monday: 10am - 5pm
                Tuesday: 10am - 5pm
                Wednesday: Closed
                Thursday: 11am - 7pm
                Friday: 10am - 5pm
                Saturday: 10am - 5pm
                Sunday: Closed
            """.trimIndent(),
            description = "Community library offering books, computers, Wi-Fi, printing services, children's activities and community events."
        ),

        // 5. Avonmouth Recycling Centre
        Facility(
            id = 5,
            name = "Avonmouth Reuse and Recycling Centre",
            category = "Recycling Centre",
            address = "Kings Weston Lane, Avonmouth, Bristol, BS11 0YS",
            latitude = 0.0,
            longitude = 0.0,
            openingHours = """
                Monday: 9am - 5pm
                Tuesday: 9am - 5pm
                Wednesday: Closed
                Thursday: Closed
                Friday: 9am - 5pm
                Saturday: 8am - 4pm
                Sunday: 8am - 4pm
                Booking required
            """.trimIndent(),
            description = "Household reuse and recycling centre serving Bristol residents. Visitors must book a visit and bring photo ID and proof of Bristol residence."
        ),

        // 6. St Philips Recycling Centre
        Facility(
            id = 6,
            name = "St Philips Reuse and Recycling Centre",
            category = "Recycling Centre",
            address = "Folly Lane, off Days Road, Bristol, BS2 0QS",
            latitude = 0.0,
            longitude = 0.0,
            openingHours = """
                Monday: 9am - 5pm
                Tuesday: 9am - 5pm
                Wednesday: 9am - 5pm
                Thursday: Closed
                Friday: Closed
                Saturday: 8am - 4pm
                Sunday: 8am - 4pm
                Booking required
            """.trimIndent(),
            description = "Household reuse and recycling centre for Bristol residents. Visitors must book a visit and bring photo ID and proof of Bristol residence."
        )
    )

    // Return all facilities to the list screen
    fun getAllFacilities(): List<Facility> {
        return facilities
    }
}
