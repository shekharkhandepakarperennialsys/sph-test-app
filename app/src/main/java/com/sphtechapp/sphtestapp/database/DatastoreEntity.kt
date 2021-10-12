package com.sphtechapp.sphtestapp.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "datastore_record")
data class DatastoreRecordEntity(
    @PrimaryKey
    var _id: Int,
    var quarter: String,
    var volume_of_mobile_data: String
) {
    @Ignore var year: String = ""
    @Ignore var total_volume_data: Double = 0.0
}