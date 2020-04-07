package jp.covid19_kagawa.covid19information2.data.mapper


import jp.covid19_kagawa.covid19information2.data.entity.NewsItem

import jp.covid19_kagawa.covid19information2.data.entity.tochigi.TochigiData
import jp.covid19_kagawa.covid19information2.entity.*

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


object TochigiMapper {
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

    fun getInspectionData(data: TochigiData): List<InspectionData> {
//        val rootData = data.inspection_persons.labels
//        val countData = data.inspection_persons.datasets.get(0).data
        val makeData = ArrayList<InspectionData>()

        makeData.add(
            InspectionData(
                0.0f,
                TimeUnit.MILLISECONDS.toHours(getMilliFromDate("0")).toFloat()
            )
        )
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

    fun getInfectionData(data: TochigiData): InfectionSummary {
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

    fun getInspectionSummaryData(data: TochigiData): InspectionSummary {

        return InspectionSummary(
            "",
            "",
            "",
            "", ""
        )
    }

    fun getInspectionDetailData(data: TochigiData): List<InspectionDetailSummary> {
        val rootData = data.inspections_summary
        val insideData = rootData.data
        val outsideData = rootData.data
        val list = ArrayList<InspectionDetailSummary>()
        list.add(
            InspectionDetailSummary(
                TimeUnit.MILLISECONDS.toHours(getMilliFromDate("0")),
                0,
                0
            )
        )
        return list
    }

    fun getContactData(data: TochigiData): ContactData {
        val rootData = data.contacts
        val entries = rootData.data
        val list = ArrayList<ContactEntry>()
        list.add(
            ContactEntry(
                TimeUnit.MILLISECONDS.toHours(getMilliFromDate("entry.日付")),
                0
            )
        )
        return ContactData("", list)
    }

    fun getEntranceData(data: TochigiData): EntranceData {

        val list = ArrayList<EntranceEntry>()
        EntranceEntry(
            TimeUnit.MILLISECONDS.toHours(getMilliFromDate("")),
            0
        )
        return EntranceData("", list)
    }
}