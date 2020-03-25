package jp.digital_future.cameraxsample.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "japan_top")
data class AreaEntity constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "content_value")
    val contentName: String,
    @ColumnInfo(name = "class_code")
    val classCode: String
) : Parcelable
