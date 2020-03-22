package jp.covid19_kagawa.covid19_kagawa.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import jp.covid19_kagawa.covid19_kagawa.R
import jp.covid19_kagawa.covid19_kagawa.ui.MarkerView

class DashboardFragment : Fragment(), SeekBar.OnSeekBarChangeListener,
    OnChartValueSelectedListener {

    private lateinit var dashboardViewModel: DashboardViewModel
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
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
//        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        dashboardViewModel.entry.observe(viewLifecycleOwner, Observer {
            it ?: return@observe
            updateGraph(it)
        })
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
        chart.setOnChartValueSelectedListener(this)
        chart.setDrawGridBackground(false)

        val markerView = MarkerView(context, R.layout.marker_view)

        markerView.chartView = chart
        chart.marker = markerView

        chart.isDragEnabled = true
        chart.isScaleXEnabled = true
        chart.isScaleYEnabled = true
        chart.setPinchZoom(true)

        chart.xAxis.enableGridDashedLine(10f, 10f, 0f)
        chart.axisRight.isEnabled = false
        chart.axisLeft.enableGridDashedLine(10f, 10f, 0f)
    }

    private fun updateGraph(values: List<>) {
        chart.axisLeft.axisMaximum = 300f
        chart.axisLeft.axisMinimum = 0f

        for (content in values) {

        }

    }


}
