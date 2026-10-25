
package com.example.britstolconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.bristolconnect.localservices.FacilityListScreen
import com.example.britstolconnect.ui.theme.BritstolConnectTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            BritstolConnectTheme(
                darkTheme = true,
                dynamicColor = false
            ) {
                FacilityListScreen()
            }
        }
    }
}
