
package com.example.bristolconnect.localservices

fun filterFacilities(
    facilities: List<Facility>,
    searchQuery: String,
    selectedCategory: String
): List<Facility> {

    val query = searchQuery.trim()

    return facilities.filter { facility ->

        // Search by name, category or address
        val matchesSearch =
            facility.name.contains(
                query,
                ignoreCase = true
            ) ||
                    facility.category.contains(
                        query,
                        ignoreCase = true
                    ) ||
                    facility.address.contains(
                        query,
                        ignoreCase = true
                    )

        // Apply the selected category
        val matchesCategory = when (selectedCategory) {
            "Libraries" -> facility.category == "Library"
            "Recycling" -> facility.category == "Recycling Centre"
            else -> true
        }

        matchesSearch && matchesCategory
    }
}
