
package com.example.bristolconnect.localservices

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val NavyBackground = Color(0xFF111827)
private val CardBackground = Color(0xFF202B3E)
private val BlueAccent = Color(0xFF2563EB)
private val LightBlue = Color(0xFF93C5FD)
private val MutedText = Color(0xFFABB8CC)

@Composable
fun FacilityDetailScreen(
    facility: Facility,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    val facilityIcon = when (facility.category) {
        "Library" -> "📖"
        "Recycling Centre" -> "♻"
        else -> "⌖"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        // Back navigation
        Button(
            onClick = onBackClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = CardBackground,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("← Back")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Facility type
        Text(
            text = "$facilityIcon  ${facility.category}",
            color = LightBlue,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Facility name
        Text(
            text = facility.name,
            color = Color.White,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Address section
        FacilityInfoCard(
            icon = "⌖",
            title = "Address",
            content = facility.address
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Opening hours section
        FacilityInfoCard(
            icon = "◷",
            title = "Opening Hours",
            content = facility.openingHours
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Facility description
        FacilityInfoCard(
            icon = "ⓘ",
            title = "About this Facility",
            content = facility.description
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Opening times may change on bank holidays. " +
                    "Check official service information before visiting.",
            color = MutedText,
            fontSize = 12.sp,
            lineHeight = 18.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // External Google Maps directions
        Button(
            onClick = {

                val destination =
                    "${facility.name}, ${facility.address}, UK"

                val encodedDestination =
                    Uri.encode(destination)

                val mapsUrl = Uri.parse(
                    "https://www.google.com/maps/dir/" +
                            "?api=1&destination=$encodedDestination"
                )

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    mapsUrl
                )

                try {
                    context.startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        context,
                        "No application available to open directions",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueAccent,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "↗  Get Directions",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Reusable card for facility information
@Composable
private fun FacilityInfoCard(
    icon: String,
    title: String,
    content: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                CardBackground,
                RoundedCornerShape(16.dp)
            )
            .padding(18.dp)
    ) {

        Text(
            text = "$icon  $title",
            color = LightBlue,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = content,
            color = MutedText,
            fontSize = 14.sp,
            lineHeight = 21.sp
        )
    }
}
