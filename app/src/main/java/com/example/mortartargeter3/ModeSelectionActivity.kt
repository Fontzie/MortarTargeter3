package com.example.mortartargeter3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class ModeSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mode_selection)

        val btnMortarMode: Button = findViewById(R.id.btnMortarMode)
        val btnFieldMode: Button = findViewById(R.id.btnFieldMode)
        val radioGroup: RadioGroup = findViewById(R.id.rgDefaultLocation)

        btnMortarMode.setOnClickListener {
            val (fallbackLat, fallbackLon) = getCoordinatesForSelectedLocation(radioGroup.checkedRadioButtonId)
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("fallback_lat", fallbackLat)
                putExtra("fallback_lon", fallbackLon)
            }
            startActivity(intent)
        }

        btnFieldMode.setOnClickListener {
            val (fallbackLat, fallbackLon) = getCoordinatesForSelectedLocation(radioGroup.checkedRadioButtonId)
            val intent = Intent(this, FieldMapPickerActivity::class.java).apply {
                putExtra("fallback_lat", fallbackLat)
                putExtra("fallback_lon", fallbackLon)
            }
            startActivity(intent)
        }
    }

    // Map radio button IDs to preset coordinates.
    private fun getCoordinatesForSelectedLocation(selectedId: Int): Pair<Double, Double> {
        return when (selectedId) {
            R.id.rbLocation1 -> Pair(-37.53881906600311, 175.18507637283486)  // San Francisco
            R.id.rbLocation2 -> Pair(-37.90982557322846, 175.5549521304944)  // Los Angeles
            R.id.rbLocation3 -> Pair(-37.823530,174.980056)   // New York
            else -> Pair(-37.7826, 175.2528)
        }
    }
}
