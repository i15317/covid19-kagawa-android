package jp.covid19_kagawa.covid19information.data.mapper


import jp.covid19_kagawa.covid19information.data.entity.InfectData
import jp.covid19_kagawa.covid19information.data.entity.NewsData
import jp.covid19_kagawa.covid19information.data.entity.NewsItem

import jp.covid19_kagawa.covid19information.entity.InspectionData
import jp.covid19_kagawa.covid19information.entity.InfectionSummary
import jp.covid19_kagawa.covid19information.entity.NewsEntity
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
                    countData.get(i) * range,
                    TimeUnit.MILLISECONDS.toHours(getMilliFromDate(rootData.get(i))).toFloat()
                )
            )
        }
        return makeData.dropWhile { makeData.count() >= count }
    }

    fun getNewsData(data: List<NewsItem>): List<NewsEntity> {
        val list = ArrayList<NewsEntity>()
        for (content in data) {
            list.add(
                NewsEntity(
                    content.date,
                    "",
                    content.text,
                    content.url,
                    ""
                )
            )
        }
        return list
    }

    fun getInfectionData(data: InfectData): InfectionSummary {
        //ここのvalueで検査人数
        val rootData = data.main_summary
        //ここのvalueで陽性患者数
        val latestData = rootData.children.last()
        //ここのvalueで入院中
        val hospital = latestData.children[0].children
        var date = Date()
        val formatter = SimpleDateFormat("""yyyy\/MM\/dd　HH:mm:ss""")
        try {
            date = formatter.parse(data.lastUpdate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        println("Today is $date")
        return InfectionSummary(
            date.getTime(), //日付
            rootData.value, //検査実施人数
            latestData.value, //陽性患者
            latestData.children[0].value,
            hospital.get(0).value,
            hospital.get(1).value,
            latestData.children.get(1).value,
            latestData.children.get(2).value
        )

    }


}