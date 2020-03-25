package jp.digital_future.cameraxsample.room.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import jp.covid19_kagawa.covid19information.Prefecture
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "pref_name")
data class PrefectureEntity constructor(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "class_code")
    val prefClassCode: String,

    @ColumnInfo(name = "pref_name")
    val prefName: String,

    @ColumnInfo(name = "pref_code")
    val prefCode: String,

    @ColumnInfo(name = "pref_enum")
    val prefEnum: Prefecture
) : Parcelable