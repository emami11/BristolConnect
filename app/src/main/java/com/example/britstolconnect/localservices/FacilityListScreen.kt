
package com.example.bristolconnect.localservices

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val NavyBackground = Color(0xFF111827)
private val CardBackground = Color(0xFF202B3E)
private val IconBackground = Color(0xFF30435F)
private val BlueAccent = Color(0xFF2563EB)
private val LightBlue = Color(0xFF93C5FD)
private val MutedText = Color(0xFFABB8CC)

@Composable
fun FacilityListScreen() {

    // Load facilities from the repository
    val facilities = remember {
        FacilityRepository().getAllFacilities()
    }

    // Remember the search term
    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    // Remember the selected category
    var selectedCategory by rememberSaveable {
        mutableStateOf("All")
    }

    // Remember the selected facility
    var selectedFacilityId by rememberSaveable {
        mutableStateOf<Int?>(null)
    }

    val selectedFacility = facilities.find {
        it.id == selectedFacilityId
    }

    // Display the selected facility's details
    if (selectedFacility != null) {

        BackHandler {
            selectedFacilityId = null
        }

        FacilityDetailScreen(
            facility = selectedFacility,
            onBackClick = {
                selectedFacilityId = null
            }
        )

    } else {

        val categories = listOf(
            "All",
            "Libraries",
            "Recycling"
        )

        // Use our reusable filtering function
        val filteredFacilities = filterFacilities(
            facilities = facilities,
            searchQuery = searchQuery,
            selectedCategory = selectedCategory
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NavyBackground)
                .padding(16.dp)
        ) {

            // Application branding
            Text(
                text = "BRISTOLCONNECT",
                color = MutedText,
                fontSize = 12.sp,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Screen title
            Text(
                text = "Local Services Finder",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Search bar
            BasicTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                cursorBrush = SolidColor(BlueAccent),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        CardBackground,
                        RoundedCornerShape(14.dp)
                    )
                    .padding(16.dp),
                decorationBox = { innerTextField ->

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "⌕",
                            color = MutedText,
                            fontSize = 22.sp
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Box(
                            modifier = Modifier.weight(1f)
                        ) {

                            if (searchQuery.isEmpty()) {
                                Text(
                                    text = "Search facilities...",
                                    color = MutedText,
                                    fontSize = 15.sp
                                )
                            }

                            innerTextField()
                        }

                        // Clear search button
                        if (searchQuery.isNotEmpty()) {

                            Text(
                                text = "✕",
                                color = MutedText,
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .clickable {
                                        searchQuery = ""
                                    }
                                    .padding(start = 12.dp)
                            )
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Category filter buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                categories.forEach { category ->

                    val isSelected =
                        selectedCategory == category

                    Button(
                        onClick = {
                            selectedCategory = category
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) {
                                BlueAccent
                            } else {
                                CardBackground
                            },
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(
                            horizontal = 14.dp,
                            vertical = 8.dp
                        )
                    ) {

                        Text(
                            text = category,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Display empty results or facility cards
            if (filteredFacilities.isEmpty()) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "⌕",
                        color = BlueAccent,
                        fontSize = 52.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "No facilities found",
                        color = Color.White,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Try a different search term or choose another category.",
                        color = MutedText,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            searchQuery = ""
                            selectedCategory = "All"
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlueAccent,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {

                        Text("Show All Facilities")
                    }
                }

            } else {

                // Scrollable facility list
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {

                    items(
                        items = filteredFacilities,
                        key = { facility -> facility.id }
                    ) { facility ->

                        FacilityCard(
                            facility = facility,
                            onClick = {
                                selectedFacilityId = facility.id
                            }
                        )
                    }
                }
            }
        }
    }
}

// Reusable facility card component
@Composable
private fun FacilityCard(
    facility: Facility,
    onClick: () -> Unit
) {

    val facilityIcon = when (facility.category) {
        "Library" -> "📖"
        "Recycling Centre" -> "♻"
        else -> "⌖"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                CardBackground,
                RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Facility icon
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    IconBackground,
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = facilityIcon,
                color = LightBlue,
                fontSize = 25.sp
            )
        }

        // Facility information
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = facility.name,
                color = Color.White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = facility.address,
                color = MutedText,
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = facility.category,
                color = LightBlue,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Navigation indicator
        Text(
            text = "›",
            color = MutedText,
            fontSize = 28.sp
        )
    }
}
