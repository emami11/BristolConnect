
package com.example.bristolconnect.localservices

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
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
private val MutedText = Color(0xFFABB8CC)

@Composable
fun FacilityDetailScreen(
    facility: Facility,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Button(
            onClick = onBackClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = CardBackground
            )
        ) {
            Text("← Back", color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = facility.name,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = facility.category,
            color = BlueAccent,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    CardBackground,
                    RoundedCornerShape(16.dp)
                )
                .padding(20.dp)
        ) {
            Text(
                text = "Address",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = facility.address,
                color = MutedText
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Opening Hours",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = facility.openingHours,
                color = MutedText
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "About this Facility",
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = facility.description,
                color = MutedText
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

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
                    android.widget.Toast.makeText(
                        context,
                        "No app available to open directions",
                        android.widget.Toast.LENGTH_LONG
                    ).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueAccent,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "↗  Get Directions",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
