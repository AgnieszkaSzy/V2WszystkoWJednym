package com.example.v2wszystkowjednym

import Measurement
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "measurements.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "measurements"
        private const val COLUMN_ID = "id"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_HEIGHT = "height"
        private const val COLUMN_WEIGHT = "weight"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_DATE TEXT,
                $COLUMN_HEIGHT REAL,
                $COLUMN_WEIGHT REAL
            )
        """
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertMeasurement(date: String, height: Float, weight: Float) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_DATE, date)
            put(COLUMN_HEIGHT, height)
            put(COLUMN_WEIGHT, weight)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllMeasurements(): List<Measurement> {
        val measurements = mutableListOf<Measurement>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM measurements", null)

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                val height = cursor.getFloat(cursor.getColumnIndexOrThrow("height"))
                val weight = cursor.getFloat(cursor.getColumnIndexOrThrow("weight"))
                measurements.add(Measurement(date, height, weight))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return measurements
    }
}