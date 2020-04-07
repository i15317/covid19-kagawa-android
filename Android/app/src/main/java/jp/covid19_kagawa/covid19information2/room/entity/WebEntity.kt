package jp.covid19_kagawa.covid19information2.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "call_data")
data class WebEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "center_pref")
    val prefectureCode: String,
    @ColumnInfo(name = "center_name")
    val centerName: String,
    @ColumnInfo(name = "center_kana")
    val centerNameByKana: String,
    @ColumnInfo(name = "center_location")
    val centerLocation: String,
    @ColumnInfo(name = "center_link")
    val centerLink: String,
    @ColumnInfo(name = "center_time")
    val centerTime: String,
    @ColumnInfo(name = "opening_time")
    val openTime: Boolean
) : Parcelable
