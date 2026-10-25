
package com.example.bristolconnect.localservices

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val NavyBackground = Color(0xFF111827)
private val CardBackground = Color(0xFF202B3E)
private val BlueAccent = Color(0xFF2563EB)
private val MutedText = Color(0xFFABB8CC)

@Composable
fun FacilityListScreen() {

    // Load the facilities from the repository
    val facilities = remember {
        FacilityRepository().getAllFacilities()
    }

    // Remember the user's search
    var searchQuery by rememberSaveable {
        mutableStateOf("")
    }

    // Remember the selected category
    var selectedCategory by rememberSaveable {
        mutableStateOf("All")
    }

    // Remember the selected facility ID
    var selectedFacilityId by rememberSaveable {
        mutableStateOf<Int?>(null)
    }

    val selectedFacility = facilities.find { facility ->
        facility.id == selectedFacilityId
    }

    // Show the details screen when a facility is selected
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

        // Filter facilities using search and category
        val filteredFacilities = facilities.filter { facility ->

            val matchesSearch =
                facility.name.contains(
                    searchQuery,
                    ignoreCase = true
                ) ||
                        facility.category.contains(
                            searchQuery,
                            ignoreCase = true
                        ) ||
                        facility.address.contains(
                            searchQuery,
                            ignoreCase = true
                        )

            val matchesCategory = when (selectedCategory) {
                "Libraries" -> facility.category == "Library"
                "Recycling" -> facility.category == "Recycling Centre"
                else -> true
            }

            matchesSearch && matchesCategory
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NavyBackground)
                .padding(16.dp)
        ) {

            // Branding
            Text(
                text = "BRISTOLCONNECT",
                color = MutedText,
                fontSize = 12.sp,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Screen heading
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
                onValueChange = { searchQuery = it },
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        CardBackground,
                        RoundedCornerShape(14.dp)
                    )
                    .padding(16.dp),
                decorationBox = { innerTextField ->
                    Box {
                        if (searchQuery.isEmpty()) {
                            Text(
                                text = "⌕  Search facilities...",
                                color = MutedText
                            )
                        }
                        innerTextField()
                    }
                }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Category filter buttons
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                categories.forEach { category ->

                    val isSelected = selectedCategory == category

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
                        Text(text = category)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Scrollable list of facilities
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = filteredFacilities,
                    key = { facility -> facility.id }
                ) { facility ->

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                CardBackground,
                                RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                selectedFacilityId = facility.id
                            }
                            .padding(16.dp)
                    ) {

                        Text(
                            text = facility.name,
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(
                            text = facility.address,
                            color = MutedText,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}
