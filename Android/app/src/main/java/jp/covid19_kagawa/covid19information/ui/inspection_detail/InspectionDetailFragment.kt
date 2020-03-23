package jp.covid19_kagawa.covid19information.ui.inspection_detail

import android.graphics.DashPathEffect
import android.graphics.RectF
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.XYMarkerView
import jp.covid19_kagawa.covid19information.actioncreator.InspectionDetailActionCreator
import jp.covid19_kagawa.covid19information.entity.InspectionDetailSummary
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.InspectionDetailStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class InspectionDetailFragment : Fragment(),
    OnChartValueSelectedListener {

    private val store: InspectionDetailStore by viewModel()
    private val actionCreator: InspectionDetailActionCreator by inject()

    //  private lateinit var seekBarY: SeekBar
    private lateinit var chart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_inspection_detail, container, false)
        setHasOptionsMenu(true)
        setupGraphWindow(root)
        observeState()
        actionCreator.getInspectionDetailData()
        return root
    }

    private fun setupGraphWindow(root: View) {
        chart = root.findViewById(R.id.chart_inspection_detail)
        chart.setOnChartValueSelectedListener(this)
        chart.setDrawBarShadow(false)
        chart.setDrawValueAboveBar(true)
        chart.description.isEnabled = false
        chart.setMaxVisibleValueCount(150)
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)
        val xl = chart.xAxis
        xl.position = XAxis.XAxisPosition.BOTTOM
        xl.setDrawAxisLine(true)
        xl.setDrawGridLines(true)
        xl.granularity = 10f
        xl.valueFormatter = object : ValueFormatter() {
            private val mFormat =
                SimpleDateFormat("MM/dd", Locale.JAPAN)

            override fun getFormattedValue(value: Float): String {
                val millis =
                    TimeUnit.HOURS.toMillis(value.toLong())
                return mFormat.format(Date(millis))
            }
        }
        val yl = chart.axisLeft
        yl.setDrawAxisLine(true)
        yl.setDrawGridLines(true)
        yl.axisMinimum = 0f // this replaces setStartAtZero(true)
        val yr = chart.axisRight
        yr.setDrawAxisLine(true)
        yr.setDrawGridLines(false)
        yr.axisMinimum = 0f // this replaces setStartAtZero(true)
        chart.setFitBars(true)
        chart.animateY(1250)

        val l = chart.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)
        l.form = Legend.LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f
        val mv = XYMarkerView(context, xl.valueFormatter)
        mv.chartView = chart // For bounds control
        chart.marker = mv // Set the marker to the chart
    }


    private fun updateGraph(src: List<InspectionDetailSummary>) {
        val barWidth = 20f

        val values =
            ArrayList<BarEntry>()

        for (content in src) {
            val tmp = floatArrayOf(content.inside.toFloat(), content.outside.toFloat())
            values.add(BarEntry(content.date.toFloat(), tmp))
        }
        val set1: BarDataSet

        if (chart.data != null &&
            chart.data.dataSetCount > 0
        ) {
            set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else { // create a dataset and give it a type
            val labels = arrayOf("地域内", "地域外")
            set1 = BarDataSet(values, "検査実施件数")
            set1.setDrawIcons(false)
            set1.stackLabels = labels
            set1.colors = getColors()!!.toMutableList()
            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f
            // text size of values

            set1.valueTextSize = 9f

            val dataSets = java.util.ArrayList<IBarDataSet>()
            dataSets.add(set1) // add the data sets
            // create a data object with the data sets
            val data = BarData(dataSets)
            data.barWidth = barWidth
            data.setValueTextSize(10f)
            // set data
            chart.data = data
        }
        chart.setFitBars(true)
        chart.invalidate()
    }


    private fun observeState() {
        store.loadedRepositoryListState.observe(this) {
            updateGraph(it)
        }

        store.inspectionNum.observe(this) {
            this.view!!.findViewById<TextView>(R.id.inspection_detail_num).text = it.toString() + "（件）"
        }

    }

    private val onValueSelectedRectF = RectF()

    override fun onValueSelected(
        e: Entry?,
        h: Highlight?
    ) {
        if (e == null) return
        val bounds: RectF = onValueSelectedRectF
        chart.getBarBounds(e as BarEntry?, bounds)
        val position = chart.getPosition(e, YAxis.AxisDependency.LEFT)

        MPPointF.recycleInstance(position)
    }

    override fun onNothingSelected() {}


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sync -> {
            actionCreator.getInspectionDetailData()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun getColors(): IntArray { // have as many colors as stack-values per entry
        val colors = IntArray(2)
        System.arraycopy(ColorTemplate.MATERIAL_COLORS, 0, colors, 0, 2)
        return colors
    }
}