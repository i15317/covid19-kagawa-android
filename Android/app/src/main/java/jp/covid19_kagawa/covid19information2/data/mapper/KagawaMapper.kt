package jp.covid19_kagawa.covid19information2.data.mapper


import jp.covid19_kagawa.covid19information2.data.entity.NewsItem
import jp.covid19_kagawa.covid19information2.data.entity.kagawa.KagawaData
import jp.covid19_kagawa.covid19information2.entity.*

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


object KagawaMapper {
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

    fun getInspectionData(data: KagawaData): List<InspectionData> {
        val rootData = data.inspection_persons.labels
        val countData = data.inspection_persons.datasets.get(0).data
        val makeData = ArrayList<InspectionData>()

        for (i in 0 until rootData.count()) {
            makeData.add(
                InspectionData(
                    countData.get(i).toFloat(),
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

    fun getInfectionData(data: KagawaData): InfectionSummary {
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

    fun getInspectionSummaryData(data: KagawaData): InspectionSummary {
        //ここの人数で検査実施人数
        val rootData = data.inspection_status_summary
        //ここの数で検査実施件数
        val inspection = rootData.children.last()
        //地域内
        val inspection_inside = inspection.children.get(0).value
        //地域外
        val inspection_outside = inspection.children.get(1).value

        return InspectionSummary(
            rootData.date,
            rootData.value.toString(),
            inspection_inside.toString(),
            inspection_outside.toString(),
            inspection.value.toString()
        )
    }

    fun getInspectionDetailData(data: KagawaData): List<InspectionDetailSummary> {
        val rootData = data.inspections_summary
        val insideData = rootData.data.県内
        val outsideData = rootData.data.その他
        val label = data.inspection_persons.labels
        val list = ArrayList<InspectionDetailSummary>()
        for (i in 0 until label.count()) {
            list.add(
                InspectionDetailSummary(
                    TimeUnit.MILLISECONDS.toHours(getMilliFromDate(label[i])),
                    insideData[i],
                    outsideData[i]
                )
            )
        }
        return list
    }

    fun getContactData(data: KagawaData): ContactData {
        val rootData = data.contacts
        val entries = rootData.data
        val list = ArrayList<ContactEntry>()
        for (entry in entries) {
            list.add(
                ContactEntry(
                    TimeUnit.MILLISECONDS.toHours(getMilliFromDate(entry.日付)),
                    entry.`13-17時` + entry.`17-21時` + entry.`9-13時`
                )
            )
        }
        return ContactData(rootData.date, list)
    }

    fun getEntranceData(data: KagawaData): EntranceData {
        val rootData = data.querents
        val entries = rootData.data
        val list = ArrayList<EntranceEntry>()
        for (entry in entries) {
            list.add(
                EntranceEntry(
                    TimeUnit.MILLISECONDS.toHours(getMilliFromDate(entry.日付)),
                    entry.小計
                )
            )
        }
        return EntranceData(rootData.date, list)
    }
}