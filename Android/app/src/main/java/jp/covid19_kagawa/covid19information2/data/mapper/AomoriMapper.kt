package jp.covid19_kagawa.covid19information2.data.mapper


import jp.covid19_kagawa.covid19information2.data.entity.NewsItem
import jp.covid19_kagawa.covid19information2.data.entity.aomori.*
import jp.covid19_kagawa.covid19information2.entity.*

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


object AomoriMapper {
    fun getMilliFromDate(dateFormat: String): Long {
        var date = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        try {
            date = formatter.parse(dateFormat)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        println("Today is $date")
        return date.time
    }


    fun getInspectionData(data: AomoriData): List<InspectionData> {
        val rootData = data.inspection_persons.labels
        val countData = data.inspection_persons.datasets.get(0).data
        val makeData = ArrayList<InspectionData>()

        for (i in 0 until rootData.count()) {
//            makeData.add(
//                InspectionData(
//                    countData.get(i).toFloat(),
//                    TimeUnit.MILLISECONDS.toHours(getMilliFromDate(rootData.get(i))).toFloat()
//                )
//            )
            makeData.add(
                InspectionData(
                    0.0f,
                    TimeUnit.MILLISECONDS.toHours(getMilliFromDate(rootData.get(i))).toFloat()
                )
            )
        }
        return makeData
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
                    "上をクリックするとWebを開きます"
                )
            )
        }
        return list
    }

    fun getInfectionData(data: AomoriData): InfectionSummary {
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
            date.time, //日付
            rootData.value, //検査実施人数
            latestData.value, //陽性患者
            latestData.children[0].value,
            hospital.get(0).value,
            hospital.get(1).value,
            latestData.children.get(1).value,
            latestData.children.get(2).value
        )

    }

    fun getInspectionSummaryData(data: AomoriData): InspectionSummary {
        //ここの人数で検査実施人数
        val rootData = data.inspection_status_summary
        //ここの数で検査実施件数
        val inspection = rootData.children.last()
        //地域内
        val inspection_inside = inspection.children.get(0).value
        //地域外
        val inspection_outside = inspection.children.get(1).value

//        return InspectionSummary(
//            rootData.date,
//            rootData.value.toString(),
//            inspection_inside.toString(),
//            inspection_outside.toString(),
//            inspection.value.toString()
//        )
        return InspectionSummary(
            rootData.date,
            "-1", "-1", "-1", "-1"
        )
    }


    fun getInspectionDetailData(data: Array<AomoriInspectionDataItem>): List<InspectionDetailSummary> {
        val formatter = SimpleDateFormat("yyyy年MM月dd日")

        val list = ArrayList<InspectionDetailSummary>()
        for (content in data) {
            try {
                val parseData = formatter.parse(content.検査日時)
                list.add(
                    InspectionDetailSummary(
                        TimeUnit.MILLISECONDS.toHours(parseData.time),
                        content.実施数.toInt(),
                        0
                    )
                )
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return list
    }

    fun getContactData(entries: Array<AomoriCallDataItem>): ContactData {

        val list = ArrayList<ContactEntry>()
        for (entry in entries) {
            val formatter = SimpleDateFormat("yyyy年MM月dd日")
            try {
                val parseData = formatter.parse(entry.受付_年月日)

                list.add(
                    ContactEntry(
                        TimeUnit.MILLISECONDS.toHours(parseData.time),
                        entry.contact.toInt()
                    )
                )
            } catch (e: ParseException) {
                e.printStackTrace()
            }

        }
        return ContactData(entries.last().受付_年月日, list)
    }

    fun getEntranceData(entries: Array<AomoriContactDataItem>): EntranceData {

        val list = ArrayList<EntranceEntry>()
        for (entry in entries) {
            val formatter = SimpleDateFormat("yyyy年MM月dd日")
            try {
                val parseData = formatter.parse(entry.受付_年月日)

                list.add(
                    EntranceEntry(
                        TimeUnit.MILLISECONDS.toHours(parseData.time),
                        entry.相談件数.toInt()
                    )
                )
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return EntranceData(entries.last().受付_年月日, list)
    }
}