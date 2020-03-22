package jp.covid19_kagawa.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import jp.covid19_kagawa.ChartActionCreator
import jp.covid19_kagawa.ChartStore
import jp.covid19_kagawa.covid19_kagawa.R
import jp.covid19_kagawa.entity.InspectionData
import jp.covid19_kagawa.ui.MarkerView
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DashboardFragment : Fragment(), OnSeekBarChangeListener {

    private val store: ChartStore by viewModel()
    private val actionCreator: ChartActionCreator by inject()
    private lateinit var tvX: TextView
    private lateinit var tvY: TextView
    private lateinit var seekBarX: SeekBar
    private lateinit var seekBarY: SeekBar
    private lateinit var chart: LineChart
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        dashboardViewModel =
//            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
////        val textView: TextView = root.findViewById(R.id.text_dashboard)
////        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
////            textView.text = it
////        })
//        dashboardViewModel.entry.observe(viewLifecycleOwner, Observer {
//            it ?: Toast.makeText(context, "Chartdata is null", Toast.LENGTH_SHORT).show()
//            updateGraph(it)
//        })
        setupGraphWindow(root)
        observeState()
        return root
    }

    private fun setupGraphWindow(root: View) {
        tvX = root.findViewById(R.id.tvXMax)
        tvY = root.findViewById(R.id.tvYMax)
        seekBarX = root.findViewById(R.id.seekBar1)
        seekBarX.setOnSeekBarChangeListener(this)

        seekBarY = root.findViewById(R.id.seekBar2)
        seekBarY.setMax(100)
        seekBarY.setOnSeekBarChangeListener(this)

        chart = root.findViewById(R.id.chart1)
        chart.setBackgroundColor(Color.WHITE)
        chart.description.isEnabled = false
        chart.setTouchEnabled(true)
        //chart.setOnChartValueSelectedListener(this)
        chart.setDrawGridBackground(false)

        val markerView = MarkerView(context, R.layout.marker_view)

        markerView.chartView = chart
        chart.marker = markerView

        chart.isDragEnabled = true
        chart.isScaleXEnabled = true
        chart.isScaleYEnabled = true
        chart.setPinchZoom(true)


        chart.xAxis.setTextSize(10f);
        chart.xAxis.setPosition(XAxis.XAxisPosition.TOP_INSIDE);
        chart.xAxis.setTextColor(Color.WHITE);
        chart.xAxis.setDrawAxisLine(false);
        chart.xAxis.setDrawGridLines(true);
        chart.xAxis.setTextColor(Color.rgb(255, 192, 56));
        chart.xAxis.setCenterAxisLabels(true);
        chart.xAxis.setGranularity(1f); // one hour
        chart.xAxis.valueFormatter = object : ValueFormatter() {
            private val mFormat =
                SimpleDateFormat("dd MMM", Locale.ENGLISH)
            //SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.JAPAN)

            override fun getFormattedValue(value: Float): String {
                val millis =
                    TimeUnit.HOURS.toMillis(value.toLong())
                return mFormat.format(Date(millis))
            }
        }
        chart.axisRight.isEnabled = false
        chart.axisLeft.enableGridDashedLine(10f, 10f, 0f)
    }

    private fun updateGraph(src: List<InspectionData>) {

        val values =
            ArrayList<Entry>()

        for (content in src) {
            values.add(Entry(content.date, content.count))
        }

        // create a dataset and give it a type
        val set1 = LineDataSet(values, "DataSet 1")
        set1.axisDependency = AxisDependency.LEFT
        set1.color = ColorTemplate.getHoloBlue()
        set1.valueTextColor = ColorTemplate.getHoloBlue()
        set1.lineWidth = 1.5f
        set1.setDrawCircles(false)
        set1.setDrawValues(false)
        set1.fillAlpha = 65
        set1.fillColor = ColorTemplate.getHoloBlue()
        set1.highLightColor = Color.rgb(244, 117, 117)
        set1.setDrawCircleHole(false)
        // text size of values
        set1.valueTextSize = 9f
        // create a data object with the data sets
        // create a data object with the data sets
        val data = LineData(set1)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(9f)

        // set data
        // set data
        chart.data = data

    }

    override fun onProgressChanged(
        seekBar: SeekBar?,
        progress: Int,
        fromUser: Boolean
    ) {
        tvX.text = seekBarX.progress.toString()
        tvY.text = seekBarY.progress.toString()
        actionCreator.getInfectData(seekBarX.progress, seekBarY.progress.toFloat())
        // redraw
        chart.invalidate()
    }


    private fun observeState() {
//        store.loadingState.observe(this) {
//            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
//            isLoading = it
//        }
        store.loadedRepositoryListState.observe(this) {

            updateGraph(it)

        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}
