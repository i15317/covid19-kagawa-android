package jp.covid19_kagawa.covid19information.data.mapper


import jp.covid19_kagawa.covid19information.data.entity.InfectData

import jp.covid19_kagawa.covid19information.entity.InspectionData
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


object InspectionMapper {
    fun getMilliFromDate(dateFormat: String): Long {
        var date = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        try {
            date = formatter.parse(dateFormat)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        println("Today is $date")
        return date.getTime()
    }

    fun getInspectionData(data: InfectData, count: Int, range: Float): List<InspectionData> {
        val rootData = data.inspection_persons.labels
        val countData = data.inspection_persons.datasets.get(0).data
        val makeData = ArrayList<InspectionData>()

        for (i in 0 until rootData.count()) {
            makeData.add(
                InspectionData(
                     countData.get(i)* range,
                    TimeUnit.MILLISECONDS.toHours(getMilliFromDate(rootData.get(i))).toFloat()
                )
            )

        }

        return makeData.dropWhile { makeData.count() >= count }
    }

}