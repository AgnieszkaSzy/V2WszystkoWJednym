package com.example.v2wszystkowjednym

class FakeDatabaseHelper : DatabaseHelper {
    override fun getAllMeasuerements(): List<Measurement> {
        return listOf(
            Measurement("11.03.2025", 170, 60),
            Measurement("13.03.2025", 160, 53)
        )
    }
}