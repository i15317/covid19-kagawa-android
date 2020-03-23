package jp.covid19_kagawa.covid19information.ui.contact

import android.graphics.DashPathEffect
import android.graphics.RectF
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import jp.covid19_kagawa.covid19information.R
import jp.covid19_kagawa.covid19information.XYMarkerView
import jp.covid19_kagawa.covid19information.actioncreator.ContactActionCreator
import jp.covid19_kagawa.covid19information.entity.ContactEntry
import jp.covid19_kagawa.covid19information.observe
import jp.covid19_kagawa.covid19information.store.ContactStore
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class ContactFragment : Fragment(), OnChartValueSelectedListener {

    private val store: ContactStore by viewModel()
    private val actionCreator: ContactActionCreator by inject()
    private lateinit var chart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contact, container, false)
        setHasOptionsMenu(true)
        setupGraphWindow(root)
        observeState()
        actionCreator.fetchContactData()
        return root
    }

    private fun setupGraphWindow(root: View) {
        chart = root.findViewById(R.id.chart_contact)
        chart.setOnChartValueSelectedListener(this)
        chart.setDrawBarShadow(false)
        chart.setDrawValueAboveBar(true)
        chart.description.isEnabled = false
        chart.setMaxVisibleValueCount(150)
        chart.setPinchZoom(false)
        chart.setDrawGridBackground(false)
        val xl = chart.xAxis
        xl.position = XAxisPosition.BOTTOM
        //   xl.typeface = tfLight
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
        l.form = LegendForm.SQUARE
        l.formSize = 9f
        l.textSize = 11f
        l.xEntrySpace = 4f
        val mv = XYMarkerView(context, xl.valueFormatter)
        mv.setChartView(chart) // For bounds control

        chart.marker = mv // Set the marker to the chart
    }

    private fun updateGraph(src: List<ContactEntry>) {
        val barWidth = 20f

        val values =
            ArrayList<BarEntry>()

        for (content in src) {
            values.add(BarEntry(content.date.toFloat(), content.value.toFloat()))
        }
        val set1: BarDataSet

        if (chart.data != null &&
            chart.data.dataSetCount > 0
        ) {
            set1 = chart.data.getDataSetByIndex(0) as BarDataSet
            set1.setValues(values)
            set1.notifyDataSetChanged()
            chart.data.notifyDataChanged()
            chart.notifyDataSetChanged()
        } else { // create a dataset and give it a type
            set1 = BarDataSet(values, "コールセンター相談件数")
            set1.setDrawIcons(false)

            // customize legend entry
            set1.setFormLineWidth(1f)
            set1.setFormLineDashEffect(DashPathEffect(floatArrayOf(10f, 5f), 0f))
            set1.setFormSize(15f)
            // text size of values

            set1.setValueTextSize(9f)

            val dataSets = java.util.ArrayList<IBarDataSet>()
            dataSets.add(set1) // add the data sets
            // create a data object with the data sets
            val data = BarData(dataSets)
            data.barWidth = barWidth
            data.setValueTextSize(10f)


            // set data
            chart.data = data
        }
    }

    private fun observeState() {
        store.loadedRepositoryListState.observe(this) {
            updateGraph(it)
        }

        store.contactNum.observe(this) {
            this.view!!.findViewById<TextView>(R.id.contact_num).text = it.toString() + "（件）"
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
        val position = chart.getPosition(e, AxisDependency.LEFT)

        MPPointF.recycleInstance(position)
    }

    override fun onNothingSelected() {}

    companion object {
        @JvmStatic
        fun newInstance(): ContactFragment {
            return ContactFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_sync -> {
            actionCreator.fetchContactData()

            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

}